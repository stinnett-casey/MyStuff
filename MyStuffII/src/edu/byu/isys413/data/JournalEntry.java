package edu.byu.isys413.data;

import java.util.*;
import java.util.List;

public class JournalEntry extends BusinessObject {
	
	@BusinessObjectField
	private Date jedate = null;
	@BusinessObjectField
	private String txnid = null;
	

	
	public JournalEntry(String id){
		super(id);
	}
	
	/**
	 * @return the jedate
	 */
	public Date getJedate() {
		return jedate;
	}

	/**
	 * @param jedate the jedate to set
	 */
	public void setJedate(Date jedate) {
		this.jedate = jedate;
		setDirty();
	}



	
	  /**
	   * Retrieves all DebitCredits that have to do with this JournalEntry
	   */
		public List<DebitCredit> getDebitCredits() throws DataException{
			return BusinessObjectDAO.getInstance().searchForList("debitcredit", new SearchCriteria("journalentryid", id));
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
	
}
