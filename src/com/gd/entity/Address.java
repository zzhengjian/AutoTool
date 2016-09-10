package com.gd.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table( name = "ConsumerProfileAddress" )
public class Address 
{
	@Id
	private long consumerProfileAddressKey;
	
	private long consumerProfileKey;
	
	private int addressTypeKey;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private boolean isPrimary;
	
	private boolean isActive;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;

	///////////////////////////
	//  Constructor          //
	///////////////////////////
	
	public Address()
	{
	}
	
	public Address(String sAddress1, String sAddress2, String sCity, String sState, String sZipCode)
	{
		this.address1 = sAddress1;
		this.address2 = sAddress2;
		this.city = sCity;
		this.state = sState;
		this.zipCode = sZipCode;
	}
	
	///////////////////////////
	//  Helper methods       //
	///////////////////////////
	
	public boolean equals(Address oAddr)
	{
		return //this.addressTypeKey == oAddr.getAddressTypeKey() &&
				this.address1.equals(oAddr.getAddress1()) &&
				this.address2.equals(oAddr.getAddress2()) &&
				//this.address3.equals(oAddr.getAddress3()) &&
				this.city.equals(oAddr.getCity()) &&
				this.state.equals(oAddr.getState()) &&
				this.zipCode.equals(oAddr.getZipCode());
		
	}
	
	public String toString()
	{
		return address1.trim() + (address2 == null?"":("" + address2.trim())) + ", " + city.trim() + ", " + state.trim() + " " + zipCode.trim();
	}
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getConsumerProfileAddressKey() {
		return consumerProfileAddressKey;
	}

	public void setConsumerProfileAddressKey(long consumerProfileAddressKey) {
		this.consumerProfileAddressKey = consumerProfileAddressKey;
	}

	public long getConsumerProfileKey() {
		return consumerProfileKey;
	}

	public void setConsumerProfileKey(long consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}

	public int getAddressTypeKey() {
		return addressTypeKey;
	}

	public void setAddressTypeKey(int addressTypeKey) {
		this.addressTypeKey = addressTypeKey;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
