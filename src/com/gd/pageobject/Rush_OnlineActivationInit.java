package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.gd.driver.Customer;

public class Rush_OnlineActivationInit extends PageObjectBase {

	public final static String url = "https://www.rushcardlive.com/prepaid/activation/online-activation-init";

	@FindBy(css = "#CardInfo_AccountNo")
	WebElement oCardInfoAccountNo_Input;

	@FindBy(css = "#CardInfo_CVV")
	WebElement oCardInfoCvv_Input;

	@FindBy(css = "#FirstName")
	WebElement oFirstName_Input;

	@FindBy(css = "#MiddleName")
	WebElement oMiddleName_Input;

	@FindBy(css = "#LastName")
	WebElement oLastName_Input;

	@FindBy(css = "#StreetAddress")
	WebElement oStreetAddress_Input;

	@FindBy(css = "#ApartmentNumber")
	WebElement oApartmentNumber_Input;
	
	@FindBy(css = "#StateList")
	WebElement oState_DropDown;

	@FindBy(css = "#City")
	WebElement oCity_Input;

	@FindBy(css = "#ZipCode")
	WebElement oZipCode_Input;

	@FindBy(css = "#SSN")
	WebElement oSsn_Input;

	@FindBy(css = "#DateOfBirth")
	WebElement oDateOfBirth_Input;

	@FindBy(css = "#ATMPin")
	WebElement oATMPin_Input;

	@FindBy(css = "#lnkChoosePinForMe")
	WebElement oChoosePinFor_Link;

	@FindBy(css = "#EmailAddress")
	WebElement oEmailAddress_Input;

	@FindBy(css = "#AccountAlerts")
	WebElement oAccountAlerts_CheckBox;

	@FindBy(css = "#MobilePhone")
	WebElement oMobilePhone_Input;

	@FindBy(css = "#BalanceAlerts")
	WebElement oBalanceAlerts_CheckBox;

	@FindBy(css = "#HomePhone")
	WebElement oHomePhone_Input;

	@FindBy(css = "#agreeEsign")
	WebElement oAgreeesign_CheckBox;

	@FindBy(css = ".esignanchor.openModal.openModal")
	WebElement oElectronicCommunicationsAgreement_Link;

	@FindBy(css = "#CheckBoxPromo")
	WebElement oCheckBoxPromo_CheckBox;

	@FindBy(css = "#mainSubmit")
	WebElement oContinue_Button;
	
	
	public void submitCustomerInfo(String registerCustomerType, String addressType)
	{
		selectAll(oFirstName_Input).sendKeys(Customer.FirstName);
		selectAll(oLastName_Input).sendKeys(Customer.LastName);
		selectAll(oMobilePhone_Input).sendKeys(Customer.CellPhone);
		selectAll(oHomePhone_Input).sendKeys(Customer.HomePhone);
		selectAll(oEmailAddress_Input).sendKeys(Customer.Email);
		selectAll(oDateOfBirth_Input).sendKeys(Customer.DOB);
		selectAll(oATMPin_Input).sendKeys(Customer.Pin);
		

		//Customer Type
		selectAll(oSsn_Input).sendKeys(Customer.getGeneratedSSN(registerCustomerType));
		
		//Address Type
		Select stateSelect = new Select(oState_DropDown);
		selectAll(oStreetAddress_Input).sendKeys(Customer.addressMap.get(addressType)[1]);
		selectAll(oApartmentNumber_Input).sendKeys(Customer.addressMap.get(addressType)[2]);
		selectAll(oCity_Input).sendKeys(Customer.addressMap.get(addressType)[3]);		
		stateSelect.selectByValue(Customer.addressMap.get(addressType)[4]);
		selectAll(oZipCode_Input).sendKeys(Customer.addressMap.get(addressType)[5]);		

	}
	
	public void submit()
	{
		oContinue_Button.click();		
	}

}