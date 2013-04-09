package edu.byu.isys413.data;


public class Payment extends BusinessObject{

	@BusinessObjectField
	private float amount = 0.0f;
	@BusinessObjectField
	private float change = 0.0f;
	@BusinessObjectField
	private String typekind = null;
	
	
	public Payment(String id) {
		super(id);
		// TODO Auto-generated constructor stub
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
	 * @return the change
	 */
	public float getChange() {
		return change;
	}


	/**
	 * @param change the change to set
	 */
	public void setChange(float change) {
		this.change = change;
		setDirty();
	}


	/**
	 * @return the type
	 */
	public String getTypekind() {
		return typekind;
	}


	/**
	 * @param type the type to set
	 */
	public void setTypekind(String typekind) {
		this.typekind = typekind;
		setDirty();
	}




}
