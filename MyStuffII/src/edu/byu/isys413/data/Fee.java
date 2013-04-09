package edu.byu.isys413.data;

public class Fee extends RevenueSource {

	@BusinessObjectField
	private float amount = 0.0f;
	@BusinessObjectField
	private String rentalid = null;
	
	public Fee(String id) {
		super(id);
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
	 * @return the rentalid
	 */
	public String getRentalid() {
		return rentalid;
	}

	/**
	 * @param rentalid the rentalid to set
	 */
	public void setRentalid(String rentalid) {
		this.rentalid = rentalid;
		setDirty();
	}

}
