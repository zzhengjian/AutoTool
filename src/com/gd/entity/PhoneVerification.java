package com.gd.entity;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table( name = "ConsumerProfilePhoneVerificationTracking" )
public class PhoneVerification 
{
	@Id
	private long consumerProfilePhoneVerificationTrackingKey;
	
	private int phoneTypeKey;
	
	private String phoneNumber;
	
	private String verificationCode;
	
	private int consumerProfileVerificationStatusKey;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;

	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getConsumerProfilePhoneVerificationTrackingKey() {
		return consumerProfilePhoneVerificationTrackingKey;
	}

	public void setConsumerProfilePhoneVerificationTrackingKey(
			long consumerProfilePhoneVerificationTrackingKey) {
		this.consumerProfilePhoneVerificationTrackingKey = consumerProfilePhoneVerificationTrackingKey;
	}

	public int getPhoneTypeKey() {
		return phoneTypeKey;
	}

	public void setPhoneTypeKey(int phoneTypeKey) {
		this.phoneTypeKey = phoneTypeKey;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
