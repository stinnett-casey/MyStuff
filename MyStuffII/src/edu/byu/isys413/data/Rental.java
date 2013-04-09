package edu.byu.isys413.data;

import java.util.Date;

public class Rental extends RevenueSource {

	@BusinessObjectField
	private Date dateout = null;
	@BusinessObjectField
	private Date datedue = null;
	@BusinessObjectField
	private Date datein = null;
	@BusinessObjectField
	private boolean remindersent = false;
	@BusinessObjectField
	private String forrentid = null;
	@BusinessObjectField
	private String employeeid = null;
	@BusinessObjectField
	private String currentcustid = null;
	
	
	public Rental(String id) {
		super(id);
	}


	/**
	 * @return the dateout
	 */
	public Date getDateout() {
		return dateout;
	}


	/**
	 * @param dateout the dateout to set
	 */
	public void setDateout(Date dateout) {
		this.dateout = dateout;
		setDirty();
	}


	/**
	 * @return the datedue
	 * @Override
	 */
	public Date getDatedue() {
		return datedue;
	}


	/**
	 * @param datedue the datedue to set
	 */
	public void setDatedue(Date datedue) {
		this.datedue = datedue;
		setDirty();
	}


	/**
	 * @return the datein
	 */
	public Date getDatein() {
		return datein;
	}


	/**
	 * @param datein the datein to set
	 */
	public void setDatein(Date datein) {
		this.datein = datein;
		setDirty();
	}


	/**
	 * @return the remindersent
	 */
	public boolean isRemindersent() {
		return remindersent;
	}


	/**
	 * @param remindersent the remindersent to set
	 */
	public void setRemindersent(boolean remindersent) {
		this.remindersent = remindersent;
		setDirty();
	}


	/**
	 * @return the forrentid
	 */
	public String getForrentid() {
		return forrentid;
	}


	/**
	 * @param forrentid the forrentid to set
	 */
	public void setForrentid(String forrentid) {
		this.forrentid = forrentid;
		setDirty();
	}
	
	/**
	 * @Override
	 */
	public int getQuantity(){
		return 1;
	}
	
	/**
	 * @Override
	 * @return
	 * @throws DataException 
	 */
	public String getProductName(String id, String itemNumber) throws DataException{
		ForRent fr = BusinessObjectDAO.getInstance().searchForBO("ForRent", new SearchCriteria("id", id));
		String name = fr.getConceptualProduct().getProduct_name();
		return name;
	}


	/**
	 * @return the employeeid
	 */
	public String getEmployeeid() {
		return employeeid;
	}


	/**
	 * @param employeeid the employeeid to set
	 */
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}


	/**
	 * @return the currentcustid
	 */
	public String getCurrentcustid() {
		return currentcustid;
	}


	/**
	 * @param currentcustid the currentcustid to set
	 */
	public void setCurrentcustid(String currentcustid) {
		this.currentcustid = currentcustid;
		setDirty();
	}

}
