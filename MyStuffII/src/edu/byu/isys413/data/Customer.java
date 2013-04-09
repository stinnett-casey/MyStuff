package edu.byu.isys413.data;

import java.util.List;

public class Customer extends BusinessObject {

	
	@BusinessObjectField
	private String first_name = null;
	@BusinessObjectField
	private String last_name = null;
	@BusinessObjectField
	private String phone = null;
	@BusinessObjectField
	private String email = null;
	@BusinessObjectField
	private String address = null;
	@BusinessObjectField
	private String city = null;
	@BusinessObjectField
	private String state = null;
	@BusinessObjectField
	private String zip = null;
	@BusinessObjectField
	private String password = null;
	@BusinessObjectField
	private String validationcode = null;
	@BusinessObjectField
	private boolean validated = false;
	
	
	public Customer(String id){
		super(id);
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	}

	  /**
	   * Retrieves all transactions that have to do with this customer
	   */
		public List<Txn> getTransactionns() throws DataException{
			return BusinessObjectDAO.getInstance().searchForList("Txn", new SearchCriteria("customerid", id));
		}
		
		public Membership getMembership() throws DataException{
			return BusinessObjectDAO.getInstance().searchForBO("Membership", new SearchCriteria("customerid", this.id));
		}


		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}


		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
			setDirty();
		}


		/**
		 * @return the validationcode
		 */
		public String getValidationcode() {
			return validationcode;
		}


		/**
		 * @param validationcode the validationcode to set
		 */
		public void setValidationcode(String validationcode) {
			this.validationcode = validationcode;
		}


		/**
		 * @return the validated
		 */
		public boolean isValidated() {
			return validated;
		}


		/**
		 * @param validated the validated to set
		 */
		public void setValidated(boolean validated) {
			this.validated = validated;
			setDirty();
		}


}
