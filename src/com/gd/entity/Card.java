package com.gd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "Card" )
public class Card 
{
	@Id
	private long cardKey;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date cardExpDate;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date cardActivationDate;
	
	private String serialNbr;
	
	private String accountKey;
	
	private int customerKey;
	
	private boolean wasLostStolen;
	
	private short cardStatusKey;
	
	private short cardStatusReasonKey;

	public long getCardKey() {
		return cardKey;
	}

	public void setCardKey(long cardKey) {
		this.cardKey = cardKey;
	}

	public java.util.Date getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(java.util.Date cardExpDate) {
		this.cardExpDate = cardExpDate;
	}

	public java.util.Date getCardActivationDate() {
		return cardActivationDate;
	}

	public void setCardActivationDate(java.util.Date cardActivationDate) {
		this.cardActivationDate = cardActivationDate;
	}

	public String getSerialNbr() {
		return null;//SysTools.decrypt(serialNbr, "QA4");
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public int getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(int customerKey) {
		this.customerKey = customerKey;
	}

	public boolean isWasLostStolen() {
		return wasLostStolen;
	}

	public void setWasLostStolen(boolean wasLostStolen) {
		this.wasLostStolen = wasLostStolen;
	}

	public short getCardStatusKey() {
		return cardStatusKey;
	}

	public void setCardStatusKey(short cardStatusKey) {
		this.cardStatusKey = cardStatusKey;
	}

	public short getCardStatusReasonKey() {
		return cardStatusReasonKey;
	}

	public void setCardStatusReasonKey(short cardStatusReasonKey) {
		this.cardStatusReasonKey = cardStatusReasonKey;
	}
	
}
