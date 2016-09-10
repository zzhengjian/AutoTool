package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gd.driver.Customer;

public class GDFlex_SendMyCard extends PageObjectBase{

	public final static String url = "https://www.greendot.com/signup/send-my-card";

	@FindBy(css = "[for='FirstName']>span")
	WebElement oFirstName_Text;

	@FindBy(css = "#FirstName")
	WebElement oFirstName_Input;

	@FindBy(css = "[for='LastName']>span")
	WebElement oLastName_Text;

	@FindBy(css = "#LastName")
	WebElement oLastName_Input;

	@FindBy(css = "[for='Street']>span")
	WebElement oStreetAddress_Text;

	@FindBy(css = "#Street")
	WebElement oStreet_Input;

	@FindBy(css = "[for='Apartment']>span")
	WebElement oAptSuiteOther_Text;

	@FindBy(css = "#Apartment")
	WebElement oApartment_Input;

	@FindBy(css = "[for='City']>span")
	WebElement oCity_Text;

	@FindBy(css = "#City")
	WebElement oCity_Input;

	@FindBy(css = "#State")
	WebElement oState_DropDown;

	@FindBy(css = "#Zip")
	WebElement oZip_Input;

	@FindBy(css = "[for='Phone']>span")
	WebElement oPhoneNumber_Text;

	@FindBy(css = "#Phone")
	WebElement oPhone_Input;

	@FindBy(css = "#IsMobilePhone")
	WebElement oIsMobilePhone_RadioButton;

	@FindBy(css = "#IsMobilePhone")
	WebElement oIsMobilePhone_RadioButton_1;

	@FindBy(css = "[for='Email']>span")
	WebElement oEmail_Text;

	@FindBy(css = "#Email")
	WebElement oEmail_Input;

	@FindBy(css = "[for='SSN']>span")
	WebElement oSocialSecurityNumber_Text;

	@FindBy(css = "#SSN")
	WebElement oSsn_Input;

	@FindBy(css = "[for='DateOfBirth']>span")
	WebElement oDateOfBirth_Text;

	@FindBy(css = "#DateOfBirth")
	WebElement oDateOfBirth_Input;

	@FindBy(css = "[for='AtmPin']>span")
	WebElement oSet4Digit_Text;

	@FindBy(css = "#AtmPin")
	WebElement oAtmPin_Input;

	@FindBy(css = "#choosePinForMeLink")
	WebElement oChooseSecurePin_Label;

	@FindBy(css = "#IsAgreedToPromoEmails")
	WebElement oIsAgreedToPromoEmails_CheckBox;

	@FindBy(css = "#IsAgreedToECA")
	WebElement oIsAgreedToECA_CheckBox;

	@FindBy(css = "#submit-button")
	WebElement oContinue_Button;
	
	
	public void submitCustomerInfo(String registerCustomerType, String addressType)
	{
		selectAll(oFirstName_Input).sendKeys(Customer.FirstName);
		selectAll(oLastName_Input).sendKeys(Customer.LastName);
		//selectAll(oPhone_Input).sendKeys(Customer.CellPhone);
		selectAll(oEmail_Input).sendKeys(Customer.Email);
		//selectAll(oDateOfBirth_Input).sendKeys(Customer.DOB);
		//selectAll(oAtmPin_Input).sendKeys(Customer.Pin);
		

		//Customer Type
		//selectAll(oSsn_Input).sendKeys(Customer.getGeneratedSSN(registerCustomerType));
		
		//Address Type
		//Select stateSelect = new Select(oState_DropDown);
		selectAll(oStreet_Input).sendKeys(Customer.addressMap.get(addressType)[1]);
		selectAll(oApartment_Input).sendKeys(Customer.addressMap.get(addressType)[2]);
		//selectAll(oCity_Input).sendKeys(Customer.addressMap.get(addressType)[3]);		
		//stateSelect.selectByValue(Customer.addressMap.get(addressType)[4]);
		selectAll(oZip_Input).sendKeys(Customer.addressMap.get(addressType)[5]);			
		
	}
	
	public void submit()
	{
		oContinue_Button.click();		
	}

}