package com.gd.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table( name = "ConsumerProfileEmail" )
public class Email 
{
	@Id
	private long consumerProfileEmailKey;
	
	private long consumerProfileKey;
	
	private int emailTypeKey;
	
	private String email;
	
	private boolean isPrimary;
	
	private boolean isActive;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;
	
	///////////////////////////
	//  Helper methods       //
	///////////////////////////
	
	boolean equals(Email oEm)
	{
		return this.emailTypeKey == oEm.getEmailTypeKey() &&
				this.email.equals(oEm.getEmail());
	}
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getConsumerProfileEmailKey() {
		return consumerProfileEmailKey;
	}
	
	public void setConsumerProfileEmailKey(long emailKey) {
		this.consumerProfileEmailKey = emailKey;
	}
	
	public long getConsumerProfileKey() {
		return consumerProfileKey;
	}

	public void setConsumerProfileKey(long consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getEmailTypeKey() {
		return emailTypeKey;
	}
	
	public void setEmailTypeKey(int emailType) {
		this.emailTypeKey = emailType;
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
