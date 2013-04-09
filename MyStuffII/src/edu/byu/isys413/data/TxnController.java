package edu.byu.isys413.data;

public class TxnController {
	
	/////////////////////////////////////////////
	///   Singleton code
	
	/** Our map of BO names to DAOs for that BO */
	private static TxnController instance = null;
	
	/** Creates a new instance of BusinessObjectDAO */
	private TxnController() {
	}//constructor
	
	/** Retrieves the single instance of this class */
	public static synchronized TxnController getInstance() throws DataException {
		if (instance == null) {
			instance = new TxnController();
		}
		return instance;
	}//getInstance
	


}
