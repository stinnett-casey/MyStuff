package edu.byu.isys413.data;

public class StoreProduct extends BusinessObject {
	public StoreProduct(String id) {
		super(id);
	}
	@BusinessObjectField
	private String storeId = null;
	@BusinessObjectField
	private String conceptualproductid = null;
	@BusinessObjectField
	private int quantity = 0;
	@BusinessObjectField
	private String shelf_location = null;
	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Store store) {
		this.storeId = store.getId();
		setDirty();
	}
	/**
	 * @return the conceptualproductid
	 */
	public String getConceptualProductId() {
		return conceptualproductid;
	}
	/**
	 * @param conceptualproductid the conceptualproductid to set
	 */
	public void setConceptualProductId(ConceptualProduct conceptualproduct) {
		this.conceptualproductid = conceptualproduct.getId();
		setDirty();
	}
	/**
	 * @return the quantity
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
	}
	/**
	 * @return the shelf_location
	 */
	public String getShelf_location() {
		return shelf_location;
	}
	/**
	 * @param shelf_location the shelf_location to set
	 */
	public void setShelf_location(String shelf_location) {
		this.shelf_location = shelf_location;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	
	
}
