package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.browser.debug.Customer;

public class Nascar_AddSecondCard {

	public final static String url = "https://www.greendot.com/racing/account/add-second-card";

	@FindBy(css = "#FirstName")
	WebElement oFirstName_Input;

	@FindBy(css = "#MiddleInitial")
	WebElement oMiddleInitial_Input;

	@FindBy(css = "#LastName")
	WebElement oLastName_Input;

	@FindBy(css = "#Birthday")
	WebElement oBirthday_Input;

	@FindBy(css = "#SSN")
	WebElement oSsn_Input;

	@FindBy(css = "#ResidentialAddressPart1")
	WebElement oResidentialAddressPart1_Input;

	@FindBy(css = "#ResidentialAddressPart2")
	WebElement oResidentialAddressPart2_Input;
	
	@FindBy(css = "#ResidentialState")
	WebElement oResidentialState_DropDown;	

	@FindBy(css = "#ResidentialCity")
	WebElement oResidentialCity_Input;

	@FindBy(css = "#ResidentialZipCode")
	WebElement oResidentialZipCode_Input;

	@FindBy(css = "#ContactNumber")
	WebElement oContactNumber_Input;

	@FindBy(css = "#Email")
	WebElement oEmail_Input;

	@FindBy(css = "#MailingAddress1")
	WebElement oMailingAddress1_Input;

	@FindBy(css = "#MailingAddress2")
	WebElement oMailingAddress2_Input;

	@FindBy(css = "#MailingCity")
	WebElement oMailingCity_Input;

	@FindBy(css = "#MailingZipCode")
	WebElement oMailingZipCode_Input;

	@FindBy(css = "#Standard")
	WebElement oStandard_RadioButton;

	@FindBy(css = "#FedEx")
	WebElement oFedEx_RadioButton;

	@FindBy(css = "#btnSubmit")
	WebElement oBtnsubmit_Input;

	public void submitCustomerInfo(String registerCustomerType, String addressType)
	{
		oFirstName_Input.sendKeys(Customer.FirstName);
		oLastName_Input.sendKeys(Customer.LastName);
		oContactNumber_Input.sendKeys(Customer.CellPhone);
		oEmail_Input.sendKeys(Customer.Email);
		oBirthday_Input.sendKeys(Customer.DOB);		

		//Customer Type
		oSsn_Input.sendKeys(Customer.getGeneratedSSN(registerCustomerType));
		
		//Address Type
		Select stateSelect = new Select(oResidentialState_DropDown);
		oResidentialAddressPart1_Input.sendKeys(Customer.addressMap.get(addressType)[1]);
		oResidentialAddressPart2_Input.sendKeys(Customer.addressMap.get(addressType)[2]);
		oResidentialCity_Input.sendKeys(Customer.addressMap.get(addressType)[3]);		
		stateSelect.selectByValue(Customer.addressMap.get(addressType)[4]);
		oResidentialZipCode_Input.sendKeys(Customer.addressMap.get(addressType)[5]);		
		
	}
	
	public void submit()
	{
		oBtnsubmit_Input.click();		
	}
	
}