/////////////////////////////////////////////////////////////////
///   This file is an example of an Object Relational Mapping in
///   the ISys Core at Brigham Young University.  Students
///   may use the code as part of the 413 course in their
///   milestones following this one, but no permission is given
///   to use this code is any other way.  Since we will likely
///   use this code again in a future year, please DO NOT post
///   the code to a web site, share it with others, or pass
///   it on in any way.


package edu.byu.isys413.data;

import java.util.*;
import java.util.List;

/**
 * An employee.  Example BO that has inheritance.
 *
 * @author Conan C. Albrecht <conan@warp.byu.edu>
 * @version 1.1
 */
public class Employee extends BusinessObject {

    @BusinessObjectField
    private String username = null;   
    @BusinessObjectField
	private String first_name = null;
    @BusinessObjectField
	private String middle_name = null;
    @BusinessObjectField
	private String last_name = null;
    @BusinessObjectField
	private Date hire_date = null;
    @BusinessObjectField
	private String phone = null;
    @BusinessObjectField
	private float salary = 0.0f;
    @BusinessObjectField
	private String storeid = null;

    /** Creates a new instance of BusinessObject */
    public Employee(String id) {
        super(id);
    }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
		setDirty();
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
		setDirty();
	}

	/**
	 * @return the middle_name
	 */
	public String getMiddle_name() {
		return middle_name;
	}

	/**
	 * @param middle_name the middle_name to set
	 */
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
		setDirty();
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
		setDirty();
	}

	/**
	 * @return the hire_date
	 */
	public Date getHire_date() {
		return hire_date;
	}

	/**
	 * @param hire_date the hire_date to set
	 */
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
		setDirty();
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
		setDirty();
	}

	/**
	 * @return the salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
		setDirty();
	}

	/**
	 * @return the storeid
	 */
	public String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(String storeid) {
		this.storeid = storeid;
		setDirty();
	}
	
	
	  /**
	   * Retrieves all transactions that have to do with this employee
	   */
		public List<Txn> getTransactionns() throws DataException{
			return BusinessObjectDAO.getInstance().searchForList("Txn", new SearchCriteria("employeeid", id));
		}
	
		
	  /**
	   * Retrieves all commissions that have to do with this employee
	   */
		public List<Commission> getCommissions() throws DataException{
			return BusinessObjectDAO.getInstance().searchForList("Commission", new SearchCriteria("employeeid", id));
		}


}   