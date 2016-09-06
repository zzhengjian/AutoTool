package com.gd.entity;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table( name = "ConsumerProfileEmailVerificationTracking" )
public class EmailVerification 
{
	@Id
	private long consumerProfileEmailVerificationTrackingKey;
	
	private int emailTypeKey;
	
	private String email;
	
	private String verificationCode;
	
	private int consumerProfileVerificationStatusKey;

	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getConsumerProfileEmailVerificationTrackingKey() {
		return consumerProfileEmailVerificationTrackingKey;
	}

	public void setConsumerProfileEmailVerificationTrackingKey(
			long consumerProfileEmailVerificationTrackingKey) {
		this.consumerProfileEmailVerificationTrackingKey = consumerProfileEmailVerificationTrackingKey;
	}

	public int getEmailTypeKey() {
		return emailTypeKey;
	}

	public void setEmailTypeKey(int emailTypeKey) {
		this.emailTypeKey = emailTypeKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public int getConsumerProfileVerificationStatusKey() {
		return consumerProfileVerificationStatusKey;
	}

	public void setConsumerProfileVerificationStatusKey(
			int consumerProfileVerificationStatusKey) {
		this.consumerProfileVerificationStatusKey = consumerProfileVerificationStatusKey;
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
