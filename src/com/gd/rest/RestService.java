package com.gd.rest;

public class RestService {
	
	private String cardNumber;
	private String cvv;
	private String expDate;
	private String pinValue;
	
	
	public RestService(String serviceType)
	{
		init(serviceType);		
	}
	
	
	private void init(String serviceType) {
		
		switch(Enum.valueOf(Service.class, serviceType))
		{
			case PinGrab:
				break;
				
			default:
				break;
		}

			
	}


	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardnumber) {
		this.cardNumber = cardnumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getPinValue() {
		return pinValue;
	}
	public void setPinValue(String pinValue) {
		this.pinValue = pinValue;
	}
	
	
	public enum Service
	{
		PinGrab,
		CardFinder
	}

}
