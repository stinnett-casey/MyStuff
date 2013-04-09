package edu.byu.isys413.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ConceptualProduct extends Product {
	
	public ConceptualProduct(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
	@BusinessObjectField
	private String product_name = null;
	@BusinessObjectField
	private String description = null;
	@BusinessObjectField
	private String manufacturer = null;
	@BusinessObjectField
	private float avg_cost = 0.0f;
	@BusinessObjectField
	private float commissionrate = 0.0f;
	@BusinessObjectField
	private String sku = null;
	
	/**
	 * @return the product_name
	 */
	public String getProduct_name() {
		return product_name;
	}
	/**
	 * @param product_name the product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
		setDirty();
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		setDirty();
	}
	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		setDirty();
	}
	/**
	 * @return the avg_cost
	 */
	public float getAvg_cost() {
		return avg_cost;
	}
	/**
	 * @param avg_cost the avg_cost to set
	 */
	public void setAvg_cost(float avg_cost) {
		this.avg_cost = avg_cost;
		setDirty();
	}
	
	/**
	 * @return the commissionrate
	 */
	public float getCommissionrate() {
		return commissionrate;
	}
	/**
	 * @param commissionrate the commissionrate to set
	 */
	public void setCommissionrate(float commissionrate) {
		this.commissionrate = commissionrate;
		setDirty();
	}
	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
		setDirty();
	}
	
	  /**
	   * Retrieves the StoreProduct relationship object for this conceptualproduct
	   */
	  public List<StoreProduct> getStoreProducts() throws DataException {
	    return BusinessObjectDAO.getInstance().searchForList("StoreProduct", new SearchCriteria("conceptualproductid", id));
	  }
	  
	  /**
	   * 
	   * @param conceptualproduct
	   * @return
	   * @Override
	   */
	  public float getProductCommission(ConceptualProduct conceptualproduct, int quantity){
		  return ((conceptualproduct.getPrice() * quantity) * conceptualproduct.commissionrate);
	  }
	  
	  
	  
	  public List<PhysicalProduct> getPhysicalProductsWithThisId() throws DataException{
		  
		  	String id = null;
			boolean used = false;		
			
			try{
				 // get our connection
		        Connection conn = ConnectionPool.getInstance().get();
		        Statement stmt = conn.createStatement();
	            try{           
	            ResultSet rs = stmt.executeQuery( "SELECT * FROM forsale, physicalproduct WHERE physicalproduct.id = forsale.id AND physicalproduct.conceptualproductid = '" + this.id + "'");
	            try {
	                while ( rs.next() ) {
	                	id = (String) rs.getObject(1);
	                	System.out.println(rs.getObject(1));
	                    used = (boolean) rs.getObject(2);
	                    System.out.println(rs.getObject(2));
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
						
		  
		  return BusinessObjectDAO.getInstance().searchForList("ForSale", new SearchCriteria("id", id), new SearchCriteria("used", used));
	  }

}
