package com.gd.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table( name = "ConsumerProfileIdentification" )
public class Identification
{
	@Id
	private long consumerProfileIdentificationKey;
	
	private long consumerProfileKey;
	
	private String personalID;
	
	private int personalIDTypeKey;
	
	private String state;
	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;

	///////////////////////////
	//  Helper methods       //
	///////////////////////////
	
	boolean equals(Identification oID)
	{
		return this.personalID.equals(oID.getPersonalID()) &&
				this.personalIDTypeKey == oID.getPersonalIDTypeKey() &&
				this.state.equals(oID.getState());
	}

	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getConsumerProfileIdentificationKey() {
		return consumerProfileIdentificationKey;
	}

	public void setConsumerProfileIdentificationKey(long consumerProfileIdentificationKey) {
		this.consumerProfileIdentificationKey = consumerProfileIdentificationKey;
	}

	public long getConsumerProfileKey() {
		return consumerProfileKey;
	}

	public void setConsumerProfileKey(long consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}

	public String getPersonalID() {
		return personalID;
	}

	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}

	public int getPersonalIDTypeKey() {
		return personalIDTypeKey;
	}

	public void setPersonalIDTypeKey(int personalIDTypeKey) {
		this.personalIDTypeKey = personalIDTypeKey;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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
