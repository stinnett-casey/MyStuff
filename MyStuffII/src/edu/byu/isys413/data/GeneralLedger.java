package edu.byu.isys413.data;



public class GeneralLedger extends BusinessObject {

	@BusinessObjectField
	private String accountname = null;
	@BusinessObjectField
	private float balance = 0.0f;
	@BusinessObjectField
	private String typekind = null;
	
	public GeneralLedger(String id){
		super(id);
	}

	/**
	 * @return the accountname
	 */
	public String getAccountname() {
		return accountname;
	}

	/**
	 * @param accountname the accountname to set
	 */
	public void setAccountname(String accountname) {
		this.accountname = accountname;
		setDirty();
	}

	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
		setDirty();
	}

	/**
	 * @return the typekind
	 */
	public String getTypekind() {
		return typekind;
	}

	/**
	 * @param typekind the typekind to set
	 */
	public void setTypekind(String typekind) {
		this.typekind = typekind;
		setDirty();
	}
	
}
