package edu.byu.isys413.data;

public class ForRent extends PhysicalProduct {

	@BusinessObjectField
	private int timesrented = 0;
	
	
	public ForRent(String id) {
		super(id);
	}


	/**
	 * @return the timesrented
	 */
	public int getTimesrented() {
		return timesrented;
	}


	/**
	 * @param timesrented the timesrented to set
	 */
	public void setTimesrented(int timesrented) {
		this.timesrented = timesrented;
		setDirty();
	}

}
