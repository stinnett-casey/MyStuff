package edu.byu.isys413.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Txn extends BusinessObject {

	@BusinessObjectField
	private Date txndate = null;
	@BusinessObjectField
	private float subtotal = 0.0f;
	@BusinessObjectField
	private float tax = 0.0f;
	@BusinessObjectField
	private float total = 0.0f;
	@BusinessObjectField
	private String customerid = null;
	@BusinessObjectField
	private String storeid = null;
	@BusinessObjectField
	private String employeeid = null;
	@BusinessObjectField
	private String paymentid = null;
	@BusinessObjectField
	private String commissionid = null;
	
	public Txn(String id){
		super(id);
	}

	/**
	 * @return the subtotal
	 */
	public float getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
		setDirty();
	}

	/**
	 * @return the tax
	 */
	public float getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(float tax) {
		this.tax = tax;
		setDirty();
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
		setDirty();
	}

	/**
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
		setDirty();
	}

	/**
	 * @return the storeid
	 */
	public String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(String storeid) {
		this.storeid = storeid;
		setDirty();
	}
		
	  /**
	   * Retrieves all RevenueSources that have to do with this transaction
	   */
		public List<RevenueSource> getRevenueSources() throws DataException{
			return BusinessObjectDAO.getInstance().searchForList("RevenueSource", new SearchCriteria("txnid", id));
		}

	/**
	 * @return the employeeid
	 */
	public String getEmployeeid() {
		return employeeid;
	}

	/**
	 * @param employeeid the employeeid to set
	 */
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}



	/**
	 * @return the txndate
	 */
	public Date getTxndate() {
		return txndate;
	}



	/**
	 * @param txndate the txndate to set
	 */
	public void setTxndate(Date txndate) {
		this.txndate = txndate;
		setDirty();
	}

	/**
	 * @return the paymentid
	 */
	public String getPaymentid() {
		return paymentid;
	}

	/**
	 * @param paymentid the paymentid to set
	 */
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	/**
	 * @return the commissionid
	 */
	public String getCommissionid() {
		return commissionid;
	}

	/**
	 * @param commissionid the commissionid to set
	 */
	public void setCommissionid(String commissionid) {
		this.commissionid = commissionid;
		setDirty();
	}

	
	public float getCommissionAmount() throws DataException{
		Commission comm = BusinessObjectDAO.getInstance().searchForBO("Commission", new SearchCriteria("id", this.commissionid));
		return comm.getAmount();
	}
	
	
	/**
	 * Gets all rentals that were associated with the current transaction.
	 * @param txn
	 * @return
	 */
	public List<Rental> getRentals(Txn txn){
		
		String id = null;
		String type = null;
		String botype = null;		
		List<Rental> rentals = new LinkedList<Rental>();
		
		try{
			 // get our connection
	        Connection conn = ConnectionPool.getInstance().get();
	        Statement stmt = conn.createStatement();
            try{           
            ResultSet rs = stmt.executeQuery( "SELECT businessobject.id, businessobject.botype FROM businessobject, revenuesource WHERE businessobject.id = revenuesource.id AND revenuesource.txnid = '" + txn.getId() + "'");
            try {
                while ( rs.next() ) {
                	id = (String) rs.getObject(1);
                    type = (String) rs.getObject(2);
                    botype = type.substring(21);
                    if (botype == "Rental"){
                    	rentals.add((Rental) BusinessObjectDAO.getInstance().read(id));
                    }
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
		
		
		return rentals;
		
	}
	
	
	/**
	 * Gets all sales that were associated with the current transaction.
	 * @param txn
	 * @return
	 */
	public List<Sale> getSales(Txn txn){
		
		String id = null;
		String type = null;
		String botype = null;		
		List<Sale> sales = new LinkedList<Sale>();
		
		try{
			 // get our connection
	        Connection conn = ConnectionPool.getInstance().get();
	        Statement stmt = conn.createStatement();
            try{           
            ResultSet rs = stmt.executeQuery( "SELECT businessobject.id, businessobject.botype FROM businessobject, revenuesource WHERE businessobject.id = revenuesource.id AND revenuesource.txnid = '" + txn.getId() + "'");
            try {
                while ( rs.next() ) {
                	id = (String) rs.getObject(1);
                    type = (String) rs.getObject(2);
                    botype = type.substring(21);
                    if (botype == "Sale"){
                    	sales.add((Sale) BusinessObjectDAO.getInstance().read(id));
                    }
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
		
		
		return sales;
		
	}
	
	
	
}
