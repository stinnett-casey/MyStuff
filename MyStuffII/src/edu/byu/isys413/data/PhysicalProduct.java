package edu.byu.isys413.data;

import java.util.*;

public class PhysicalProduct extends Product {

	
	@BusinessObjectField
	private String serialnumber = null;
	@BusinessObjectField
	private String shelflocation = null;
	@BusinessObjectField
	private Date datepurchased = null;
	@BusinessObjectField
	private float cost = 0.0f;
	@BusinessObjectField
	private String status = null;
	@BusinessObjectField
	private float commissionrate = 0.0f;
	@BusinessObjectField
	private String conceptualproductid = null;
	@BusinessObjectField
	private String typekind = "";
	
	
	public PhysicalProduct(String id){
		super(id);
	}


	/**
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}



	/**
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
		setDirty();
	}



	/**
	 * @return the shelflocation
	 */
	public String getShelflocation() {
		return shelflocation;
	}



	/**
	 * @param shelflocation the shelflocation to set
	 */
	public void setShelflocation(String shelflocation) {
		this.shelflocation = shelflocation;
		setDirty();
	}



	/**
	 * @return the datepurchased
	 */
	public Date getDatepurchased() {
		return datepurchased;
	}



	/**
	 * @param datepurchased the datepurchased to set
	 */
	public void setDatepurchased(Date datepurchased) {
		this.datepurchased = datepurchased;
		setDirty();
	}



	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}



	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
		setDirty();
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the conceptualproductid
	 */
	public String getConceptualproductid() {
		return conceptualproductid;
	}


	/**
	 * @param conceptualproductid the conceptualproductid to set
	 */
	public void setConceptualproductid(String conceptualproductid) {
		this.conceptualproductid = conceptualproductid;
		setDirty();
	}
	
	public ConceptualProduct getConceptualProduct() throws DataException{
		return BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("id", conceptualproductid));
	}
	
	/**
	 * 
	 * @param physicalproduct
	 * @return
	 * @throws DataException 
	 * @Override
	 */	
	public float getProductCommission(PhysicalProduct physicalproduct, int quantity) throws DataException{
		ConceptualProduct concep;
		concep = BusinessObjectDAO.getInstance().read(conceptualproductid);
		return (((physicalproduct.getPrice() * quantity) * physicalproduct.commissionrate) + ((concep.getPrice() * quantity) * concep.getCommissionrate()));
	}


	/**
	 * @return the typekind
	 */
	public String getTypekind() {
		return typekind;
	}


	/**
	 * @param typekind the typekind to set
	 */
	public void setTypekind(String typekind) {
		this.typekind = typekind;
		setDirty();
	}



	public String getUsed(){
		return "";
	}
	
	
}
