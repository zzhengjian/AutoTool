package com.gd.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table( name = "ConsumerProfilePhone" )
public class Phone 
{
	@Id
	private long consumerProfilePhoneKey;
	
	private long consumerProfileKey;
	
	private String phoneNumber;
	
	private int phoneTypeKey;
	
	private boolean isPrimary;
	
	private boolean isActive;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;
	
	///////////////////////////
	//  Helper methods       //
	///////////////////////////
	
	boolean equals(Phone oPh)
	{
		return this.phoneNumber.equals(oPh.getPhoneNumber()) &&
				this.phoneTypeKey == oPh.getPhoneTypeKey();

	}
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getConsumerProfilePhoneKey() {
		return consumerProfilePhoneKey;
	}
	
	public void setConsumerProfilePhoneKey(long phoneKey) {
		this.consumerProfilePhoneKey = phoneKey;
	}
	
	public long getConsumerProfileKey() {
		return consumerProfileKey;
	}

	public void setConsumerProfileKey(long consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getPhoneTypeKey() {
		return phoneTypeKey;
	}
	
	public void setPhoneTypeKey(int phoneType) {
		this.phoneTypeKey = phoneType;
	}
	
	public boolean isPrimary() {
		return isPrimary;
	}
	
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
}
