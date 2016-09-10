package com.gd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "TSysQueue")
public class TSysQueue {
	
	@Id
	private int tSysQueueKey;
	
	private int sysUserKey;
	
	private short cmdCodeKey;
	
	private int customerKey;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date requestDate;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date responseDate;
	
	private String serialNbr;
	
	//////////////////////
	//Getters & Setters//
	////////////////////

	public int gettSysQueueKey() {
		return tSysQueueKey;
	}

	public void settSysQueueKey(int tSysQueueKey) {
		this.tSysQueueKey = tSysQueueKey;
	}

	public int getSysUserKey() {
		return sysUserKey;
	}

	public void setSysUserKey(int sysUserKey) {
		this.sysUserKey = sysUserKey;
	}

	public short getCmdCodeKey() {
		return cmdCodeKey;
	}

	public void setCmdCodeKey(short cmdCodeKey) {
		this.cmdCodeKey = cmdCodeKey;
	}

	public int getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(int customerKey) {
		this.customerKey = customerKey;
	}

	public java.util.Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(java.util.Date requestDate) {
		this.requestDate = requestDate;
	}

	public java.util.Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(java.util.Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getSerialNbr() {
		return serialNbr;
	}

	public void setSerialNbr(String serialNbr) {
		this.serialNbr = serialNbr;
	}
	
	
	
	
}
