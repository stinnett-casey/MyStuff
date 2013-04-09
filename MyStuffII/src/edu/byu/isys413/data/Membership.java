package edu.byu.isys413.data;

import java.util.Date;

public class Membership extends BusinessObject {

	@BusinessObjectField
	private String ccnumber = "";
	@BusinessObjectField
	private String customerid = "";
	
	
	public Membership(String id) {
		super(id);
	}


	/**
	 * @return the ccnumber
	 */
	public String getCcnumber() {
		return ccnumber;
	}


	/**
	 * @param ccnumber the ccnumber to set
	 */
	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
		setDirty();
	}


	/**
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}


	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
		setDirty();
	}


	
	
}
