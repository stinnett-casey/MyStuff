package edu.byu.isys413.data;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.MessagingException;

public class BatchController {
	
	
	/////////////////////////////////////////////
	///   Singleton code
	
	/** Our map of BO names to DAOs for that BO */
	private static BatchController instance = null;
	
	/** Creates a new instance of BusinessObjectDAO */
	private BatchController() {
	}//constructor
	
	/** Retrieves the single instance of this class */
	public static synchronized BatchController getInstance() throws DataException {
		if (instance == null) {
			instance = new BatchController();
		}
		
		return instance;
	}
	
	
	public void emailOverdueRentalCustomers() throws DataException, UnsupportedEncodingException, MessagingException{
		Calendar today = Calendar.getInstance();
		
		List<Rental> rentals = BusinessObjectDAO.getInstance().searchForList("Rental", new SearchCriteria("datein", null, SearchCriteria.NULL), new SearchCriteria("remindersent", false));
		
		for(Rental r: rentals){
			if (r.getDatedue().compareTo(today.getTime()) < 0){
				Customer cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id", r.getCurrentcustid()));
				BatchEmail.send(MailSettings.smtpHost, MailSettings.smtpUserName, cust.getEmail(), "MyStuff rental overdue", "Dear "
					    + cust.getFirst_name() + " " + cust.getLast_name() + ", \n\t The item that you are currently renting was due yesterday. Please remember to bring" +
						" back the item and only a small charge will be administered. If you keep the item for more than ten days past yesterday, we will automatically" +
					    " charge your credit card for the replacement amount.\n\n Thank you for your understanding! \n\n -MyStuff");
			}
		}
	}

	public void emailChargeChange() throws DataException, UnsupportedEncodingException, MessagingException {
		
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, -10);
		
		List<Rental> rentals = BusinessObjectDAO.getInstance().searchForList("Rental", new SearchCriteria("datein", null, SearchCriteria.NULL));
		
		for(Rental r: rentals){
			System.out.println(r.getCurrentcustid());
			System.out.println(r.getDatedue());
			if (today.getTime().compareTo(r.getDatedue()) == 0){
				Customer cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id", r.getCurrentcustid()));
				ForRent forrent = BusinessObjectDAO.getInstance().searchForBO("ForRent", new SearchCriteria("id", r.getForrentid()));
				ConceptualRental cr = BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", forrent.getConceptualProduct()));
				
				
				BatchEmail.send(MailSettings.smtpHost, MailSettings.smtpUserName, cust.getEmail(), "MyStuff rental 10 days overdue", "Dear "
					    + cust.getFirst_name() + " " + cust.getLast_name() + ", \n\t The item that you rented is now ten days overdue. As promised," +
						" we will be charging your credit card a total of \n " + cr.getPrice_per_day() * 10 + "  (Fee per day times 10)\n+ " + cr.getReplacement_price() + "  (Replacement Price)\n" +
					    ((cr.getPrice_per_day()*10) + cr.getReplacement_price()) + "  (Total).\n\n\t We hope you will enjoye your newly purchased product and return. \n-MyStuff");
				
				forrent.setStatus("sold");
				forrent.setTypekind("sale");
				forrent.save();
				
				Txn txn = BusinessObjectDAO.getInstance().create("Txn");
				txn.setCustomerid(cust.getId());
				txn.setObjectAlreadyInDB(false);
				txn.setDirty();
				txn.setSubtotal(((cr.getPrice_per_day()*10) + cr.getReplacement_price()));
				txn.setTax(0);
				txn.setTotal(((cr.getPrice_per_day()*10) + cr.getReplacement_price()));
			}
		}
		
	}
	
	
	

}
