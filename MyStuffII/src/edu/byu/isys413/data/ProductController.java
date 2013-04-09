package edu.byu.isys413.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ProductController {
	
	private PhysicalProduct physicalproduct = null;
	private ConceptualProduct conceptualproduct = null;

	/////////////////////////////////////////////
	///   Singleton code
	
	/** Our map of BO names to DAOs for that BO */
	private static ProductController instance = null;
	
	/** Creates a new instance of BusinessObjectDAO */
	private ProductController() {
	}//constructor
	
	/** Retrieves the single instance of this class */
	public static synchronized ProductController getInstance() throws DataException {
		if (instance == null) {
			instance = new ProductController();
		}
		return instance;
	}//getInstance

		
	public PhysicalProduct getCorrectPhysical(String serialNum){
		
		String id = null;
		String type = null;
		String botype = null;		
		
		try{
			 // get our connection
	        Connection conn = ConnectionPool.getInstance().get();
	        Statement stmt = conn.createStatement();
            try{           
            ResultSet rs = stmt.executeQuery( "SELECT businessobject.id, businessobject.botype FROM businessobject, physicalproduct WHERE businessobject.id = physicalproduct.id AND physicalproduct.serialnumber = '" + serialNum + "'");
            try {
                while ( rs.next() ) {
                	id = (String) rs.getObject(1);
                    type = (String) rs.getObject(2);
                    botype = type.substring(21);
                }
            } finally {
                try { rs.close(); } catch (Throwable ignore) { /* Propagate the original exception
        instead of this one that you may want just logged */ }
            }
        } finally {
            try { stmt.close(); } catch (Throwable ignore) { /* Propagate the original exception
        instead of this one that you may want just logged */ }
        }
		}catch(Exception e3){
			e3.printStackTrace();
		}
		
		try {
				physicalproduct = BusinessObjectDAO.getInstance().searchForBO(botype, new SearchCriteria("id", id));			
			} catch (DataException e2) {
				e2.printStackTrace();
			}					
		
		return physicalproduct;
	}



}
	
	
	
	
	

