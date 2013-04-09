package edu.byu.isys413.data;

public class ConceptualRental extends BusinessObject {
		
	@BusinessObjectField
	private float price_per_day = 0.0f;
	@BusinessObjectField
	private float replacement_price = 0.0f;
	
	
	public ConceptualRental(String id) {
		super(id);
	}
	

	/**
	 * @return the price_per_day
	 */
	public float getPrice_per_day() {
		return price_per_day;
	}
	/**
	 * @param price_per_day the price_per_day to set
	 */
	public void setPrice_per_day(float price_per_day) {
		this.price_per_day = price_per_day;
		setDirty();
	}
	/**
	 * @return the replacement_price
	 */
	public float getReplacement_price() {
		return replacement_price;
	}
	/**
	 * @param replacement_price the replacement_price to set
	 */
	public void setReplacement_price(float replacement_price) {
		this.replacement_price = replacement_price;
		setDirty();
	}

}
