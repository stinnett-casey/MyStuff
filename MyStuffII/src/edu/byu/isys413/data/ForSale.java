package edu.byu.isys413.data;

public class ForSale extends PhysicalProduct {

	@BusinessObjectField
	private boolean used = false;
	
	public ForSale(String id) {
		super(id);
	}

	/**
	 * @return the used
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(boolean used) {
		this.used = used;
		setDirty();
	}
	
	/**
	 * @Override
	 */
	public String getUsed(){
		if (this.used == true){
			return "used";
		}
		else{
			return "new";
		}
			
	}

}
