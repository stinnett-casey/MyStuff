package edu.byu.isys413.data;

import java.util.Date;



public class RevenueSource extends BusinessObject {

	
	@BusinessObjectField
	private float chargeamount = 0.0f;
	@BusinessObjectField
	private String typekind = null;
	@BusinessObjectField
	private String txnid = null;
	
	
	public RevenueSource(String id){
		super(id);
	}

	/**
	 * @return the chargeamount
	 */
	public float getChargeamount() {
		return chargeamount;
	}

	/**
	 * @param chargeamount the chargeamount to set
	 */
	public void setChargeamount(float chargeamount) {
		this.chargeamount = chargeamount;
		setDirty();
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

	/**
	 * @return the txnid
	 */
	public String getTxnid() {
		return txnid;
	}

	/**
	 * @param txnid the txnid to set
	 */
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	public Object getProductid() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductName(Object productid, String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getProductPrice(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getDatedue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
