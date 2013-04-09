package edu.byu.isys413.data;

import java.util.Date;

public class AccountingController {
	
	private DebitCredit dc = null;
	private JournalEntry journ = null;
	private Rental rental = null;
	private Sale sale = null;
	private float rentalsTotal = 0.0f;
	private float salesTotal = 0.0f;
	
	/////////////////////////////////////////////
	///   Singleton code
	
	/** Our map of BO names to DAOs for that BO */
	private static AccountingController instance = null;
	
	/** Creates a new instance of BusinessObjectDAO */
	private AccountingController() {
	}//constructor
	
	/** Retrieves the single instance of this class */
	public static synchronized AccountingController getInstance() throws DataException {
		if (instance == null) {
			instance = new AccountingController();
		}
		return instance;
	}
	
	
/**
 * Sets and saves all the debits and credits for the general ledger.	
 * @throws DataException 
 */
public void createJournalEntries(Txn txn) throws DataException{
	try {
		journ = BusinessObjectDAO.getInstance().create("JournalEntry");
		journ.setJedate(new Date());
		journ.save();
		
		for(Rental r: txn.getRentals(txn)){
			rental = BusinessObjectDAO.getInstance().searchForBO("Rental", new SearchCriteria("id", r.getId()));
			rentalsTotal += rental.getChargeamount();
		}
		
		for(Sale s: txn.getSales(txn)){
			sale = BusinessObjectDAO.getInstance().searchForBO("Sale", new SearchCriteria("id", s.getId()));
			salesTotal += sale.getChargeamount();
		}
		
		
	} catch (DataException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}

	//Creates Debits and Credits
	for(int je=0; je<7; je++){
		try {
			dc = BusinessObjectDAO.getInstance().create("DebitCredit");
		} catch (DataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(je==0){
			dc.setAmount(txn.getTotal());
			dc.setDebit(true);
			dc.setGeneralledgerid("generalledger1");
			dc.setGeneralledgername("Cash");
			dc.setJournalentryid(journ.getId());
			try {
				dc.save();
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(je==1){
			dc.setAmount(txn.getTax());
			dc.setDebit(false);
			dc.setGeneralledgerid("generalledger2");
			dc.setGeneralledgername("Sales Tax");
			dc.setJournalentryid(journ.getId());
			try {
				dc.save();
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(je==2){
			dc.setAmount(salesTotal); //This is where the total from all the sales goes
			dc.setDebit(true);
			dc.setGeneralledgerid("generalledger3");
			dc.setGeneralledgername("Sales Revenue");
			dc.setJournalentryid(journ.getId());
			try {
				dc.save();
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(je==3){
			dc.setAmount(txn.getCommissionAmount());
			dc.setDebit(false);
			dc.setGeneralledgerid("generalledger4");
			dc.setGeneralledgername("Commission Expense");
			dc.setJournalentryid(journ.getId());
			try {
				dc.save();
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(je==4){
			dc.setAmount(txn.getCommissionAmount());
			dc.setDebit(true);
			dc.setGeneralledgerid("generalledger5");
			dc.setGeneralledgername("Commission Payable");
			dc.setJournalentryid(journ.getId());
			try {
				dc.save();
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(je==5){
			dc.setAmount(rentalsTotal);
			dc.setDebit(true);
			dc.setGeneralledgerid("generalledger6");
			dc.setGeneralledgername("Rental Revenue");
			dc.setJournalentryid(journ.getId());
			try {
				dc.save();
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(je==6){
			dc.setAmount(0);
			dc.setDebit(true);
			dc.setGeneralledgerid("generalledger7");
			dc.setGeneralledgername("Late Fee");
			dc.setJournalentryid(journ.getId());
			try {
				dc.save();
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

}
