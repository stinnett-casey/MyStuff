package edu.byu.isys413.data;

import java.util.List;

public class Store extends BusinessObject {
	
	public Store(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	@BusinessObjectField
	private String location = null;
	@BusinessObjectField
	private String managerid = null;
	@BusinessObjectField
	private String phone = null;
	@BusinessObjectField
	private String address = null;
	@BusinessObjectField
	private String city = null;
	@BusinessObjectField
	private String state = null;
	@BusinessObjectField
	private String zip = null;
	@BusinessObjectField
	private float salestaxrate = 0.0f;
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
		setDirty();
	}
	/**
	 * @return the manager
	 */

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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
		setDirty();
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
		setDirty();
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
		setDirty();
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
		setDirty();
	}
	/**
	 * @return the employees
	 */

	
	  /**
	   * Retrieves the employees for this store.
	   */
	  public List<Employee> getStoreEmployees() throws DataException {
	    return BusinessObjectDAO.getInstance().searchForList("Employee", new SearchCriteria("storeid", id));
	  }
	
	  /**
	   * Retrieves the StoreProduct relationship object for this store.
	   */
	  public List<StoreProduct> getStoreProducts() throws DataException {
	    return BusinessObjectDAO.getInstance().searchForList("StoreProduct", new SearchCriteria("storeid", id));
	  }
	  
	  /**
	   * Retrieves all transactions that happened at this store
	   */
		public List<Txn> getTransactionns() throws DataException{
			return BusinessObjectDAO.getInstance().searchForList("Txn", new SearchCriteria("storeid", id));
		}
	/**
	 * @return the managerid
	 */
	public String getManagerid() {
		return managerid;
	}
	/**
	 * @param managerid the managerid to set
	 */
	public void setManagerid(String managerid) {
		this.managerid = managerid;
		setDirty();
	}
	/**
	 * @return the salestaxrate
	 */
	/**
	 * @return the salestaxrate
	 */
	public float getSalestaxrate() {
		return salestaxrate;
	}
	/**
	 * @param salestaxrate the salestaxrate to set
	 */
	public void setSalestaxrate(float salestaxrate) {
		this.salestaxrate = salestaxrate;
	}

	
	
}
