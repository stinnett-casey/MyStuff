package edu.byu.isys413.data;


public class Product extends BusinessObject {

	@BusinessObjectField
	private float price = 0.0f;
	@BusinessObjectField
	private boolean physical = true;
	
	public Product(String id){
		super(id);
	}	
	
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
		setDirty();
	}

	/**
	 * @return the physical
	 */
	public boolean isPhysical() {
		return physical;
	}

	/**
	 * @param physical the physical to set
	 */
	public void setPhysical(boolean physical) {
		this.physical = physical;
		setDirty();
	}
	
	
	public void getProductCommission(){	
	}
	
	
	
	
}
