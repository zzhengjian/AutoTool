package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.gd.driver.Customer;

public class GDFlex_1199seiu extends PageObjectBase {

	public final static String url = "https://www.greendot.com/1199seiu";

	@FindBy(css = "#FirstName")
	WebElement oFirstName_Input;

	@FindBy(css = "#LastName")
	WebElement oLastName_Input;

	@FindBy(css = "#Street")
	WebElement oStreet_Input;

	@FindBy(css = "#Apartment")
	WebElement oApartment_Input;
	
	@FindBy(css = "#State")
	WebElement oState_DropDown;

	@FindBy(css = "#City")
	WebElement oCity_Input;

	@FindBy(css = "#Zip")
	WebElement oZip_Input;

	@FindBy(css = "#Phone")
	WebElement oPhone_Input;

	@FindBy(css = "#PhoneType")
	WebElement oPhoneType_RadioButton;

	@FindBy(css = "#PhoneType")
	WebElement oPhoneType_RadioButton_1;

	@FindBy(css = "#Email")
	WebElement oEmail_Input;

	@FindBy(css = "#SSN")
	WebElement oSsn_Input;

	@FindBy(css = ".input-module--ssn>div>div:nth-of-type(2)>a")
	WebElement oInputHelpLink_Link;

	@FindBy(css = "#DateOfBirth")
	WebElement oDateOfBirth_Input;

	@FindBy(css = "#ATMPin")
	WebElement oATMPin_Input;

	@FindBy(css = "#lnkChoosePinForMe")
	WebElement oChooseSecurePin_Link;

	@FindBy(css = "#IsAgreedToPromoEmails")
	WebElement oIsAgreedToPromoEmails_CheckBox;

	@FindBy(css = "#IsAgreedToEmailAlerts")
	WebElement oIsAgreedToEmailAlerts_CheckBox;

	@FindBy(css = "#IsAgreedToTextAlerts")
	WebElement oIsAgreedToTextAlerts_CheckBox;

	@FindBy(css = "#IsAgreedToECA")
	WebElement oIsAgreedToECA_CheckBox;

	@FindBy(xpath = ".//button[contains(text(),'CONTINUE')]")
	WebElement oContinue_Button;

	
	public void submitCustomerInfo(String registerCustomerType, String addressType)
	{
		selectAll(oFirstName_Input).sendKeys(Customer.FirstName);
		selectAll(oLastName_Input).sendKeys(Customer.LastName);
		selectAll(oPhone_Input).sendKeys(Customer.CellPhone);
		selectAll(oEmail_Input).sendKeys(Customer.Email);
		selectAll(oDateOfBirth_Input).sendKeys(Customer.DOB);
		selectAll(oATMPin_Input).sendKeys(Customer.Pin);
		

		//Customer Type
		selectAll(oSsn_Input).sendKeys(Customer.getGeneratedSSN(registerCustomerType));
		
		//Address Type
		Select stateSelect = new Select(oState_DropDown);
		selectAll(oStreet_Input).sendKeys(Customer.addressMap.get(addressType)[1]);
		selectAll(oApartment_Input).sendKeys(Customer.addressMap.get(addressType)[2]);
		selectAll(oCity_Input).sendKeys(Customer.addressMap.get(addressType)[3]);		
		stateSelect.selectByValue(Customer.addressMap.get(addressType)[4]);
		selectAll(oZip_Input).sendKeys(Customer.addressMap.get(addressType)[5]);		
		
	}
	
	public void submit()
	{
		oContinue_Button.click();		
	}

}