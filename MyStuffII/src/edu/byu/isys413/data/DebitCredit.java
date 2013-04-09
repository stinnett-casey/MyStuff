package edu.byu.isys413.data;


public class DebitCredit extends BusinessObject {

	//If true, then object is a debit
	@BusinessObjectField
	private boolean debit = true;
	@BusinessObjectField
	private String generalledgername = null;
	@BusinessObjectField
	private String generalledgerid = null;
	@BusinessObjectField
	private float amount = 0.0f;
	@BusinessObjectField
	private String journalentryid = null;
	
	
	public DebitCredit(String id){
		super(id);
	}

	/**
	 * @return the debit
	 */
	public boolean isDebit() {
		return debit;
	}

	/**
	 * @param debit the debit to set
	 */
	public void setDebit(boolean debit) {
		this.debit = debit;
		setDirty();
	}

	/**
	 * @return the generalledgername
	 */
	public String getGeneralledgername() {
		return generalledgername;
	}

	/**
	 * @param generalledgername the generalledgername to set
	 */
	public void setGeneralledgername(String generalledgername) {
		this.generalledgername = generalledgername;
		setDirty();
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
		setDirty();
	}

	/**
	 * @return the journalentryid
	 */
	public String getJournalentryid() {
		return journalentryid;
	}

	/**
	 * @param journalentryid the journalentryid to set
	 */
	public void setJournalentryid(String journalentryid) {
		this.journalentryid = journalentryid;
		setDirty();
	}

	/**
	 * @return the generalledgerid
	 */
	public String getGeneralledgerid() {
		return generalledgerid;
	}

	/**
	 * @param generalledgerid the generalledgerid to set
	 */
	public void setGeneralledgerid(String generalledgerid) {
		this.generalledgerid = generalledgerid;
		setDirty();
	}
	
}
