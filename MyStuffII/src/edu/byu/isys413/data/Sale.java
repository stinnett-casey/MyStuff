package edu.byu.isys413.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Sale extends RevenueSource {
	
	@BusinessObjectField
	private int quantity = 0;
	@BusinessObjectField
	private String productid = null;
	private PhysicalProduct physicalProduct = null;
	private ConceptualProduct conceptualProduct = null;
	
	public Sale(String id){
		super(id);
	}

	/**
	 * @return the quantity
	 * @Override
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		setDirty();
	}

	/**
	 * @return the productid
	 * @Override
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * @param productid the productid to set
	 */
	public void setProdid(ConceptualProduct conceptualproduct) {
		this.productid = conceptualproduct.getId();
		setDirty();
	}
	
	public void setProdid(PhysicalProduct physicalproduct) {
		this.productid = physicalproduct.getId();
		setDirty();
	}
	
	
	public PhysicalProduct getPhysicalProduct(String productid) throws DataException{
		return BusinessObjectDAO.getInstance().searchForBO("PhysicalProduct", new SearchCriteria("id", productid));
	}
	
	public ConceptualProduct getConceptualProduct(String productid) throws DataException{
		return BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("id", productid));
	}
	
	/**
	 * 
	 * @param id
	 * @param productNumber
	 * @return
	 * @throws DataException
	 * @Override
	 */
	
	public String getProductName(String id, String productNumber) throws DataException{
			
		if (productNumber.contains("r") || productNumber.contains("r")){
			physicalProduct = ProductController.getInstance().getCorrectPhysical(productNumber);
		}
		else{
			conceptualProduct = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("id", id));
		}
		
		if (physicalProduct == null){
			return conceptualProduct.getProduct_name();
		}
		else{
			return physicalProduct.getConceptualProduct().getProduct_name();
		}
	}
		
	
	/**
	 * @param itemNumber
	 * @return
	 * @throws DataException
	 * @Override
	 */
	public String getProductPrice(String itemnumber){
		
		if (itemnumber.contains("s") || itemnumber.contains("r")){
			try {
				physicalProduct = ProductController.getInstance().getCorrectPhysical(itemnumber);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				conceptualProduct = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("sku", itemnumber));
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (physicalProduct == null){
			return String.valueOf(conceptualProduct.getPrice());
		}
		else{
			return String.valueOf(physicalProduct.getPrice());
		}	
	}
	

	/**
	 * @param productid the productid to set
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}
	
	
	/**
	 * @Override
	 * @return
	 */
	public String getDueDate(){
		return "Not for rent";
	}
		
	}
	


