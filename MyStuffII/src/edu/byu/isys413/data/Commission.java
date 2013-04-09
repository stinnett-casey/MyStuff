package edu.byu.isys413.data;


import java.util.*;

public class Commission extends BusinessObject {

	
	@BusinessObjectField
	private String employeeid = null;
	@BusinessObjectField
	private float amount = 0.0f;
	@BusinessObjectField
	private Date commdate = null;
	
	
	public Commission(String id){
		super(id);
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
		setDirty();
	}


	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
		setDirty();
	}


	/**
	 * @return the commdate
	 */
	public Date getCommdate() {
		return commdate;
	}


	/**
	 * @param commdate the commdate to set
	 */
	public void setCommdate(Date commdate) {
		this.commdate = commdate;
		setDirty();
	}

	/**
	 * @return the empid
	 */

}
