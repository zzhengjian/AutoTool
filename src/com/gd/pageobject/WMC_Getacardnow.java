package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.gd.driver.Customer;

public class WMC_Getacardnow extends PageObjectBase{

	public final static String url = "https://www.walmartmoneycard.com/getacardnow";

	@FindBy(css = "#FirstName")
	WebElement oFirstName_Input;

	@FindBy(css = "#LastName")
	WebElement oLastName_Input;

	@FindBy(css = "#Address1")
	WebElement oAddress1_Input;

	@FindBy(css = "#Address2")
	WebElement oAddress2_Input;
	
	@FindBy(css = "#State")
	WebElement oState_DropDown;

	@FindBy(css = "#City")
	WebElement oCity_Input;

	@FindBy(css = "#ZIP")
	WebElement oZip_Input;

	@FindBy(css = "#Email")
	WebElement oEmail_Input;

	@FindBy(css = "#PhoneMobile")
	WebElement oPhoneMobile_Input;

	@FindBy(css = "#SocialSecurityNumber")
	WebElement oSocialSecurityNumber_Input;

	@FindBy(css = "#BirthDate")
	WebElement oBirthDate_Input;

	@FindBy(css = "#CardPIN")
	WebElement oCardPIN_Input;

	@FindBy(css = "#lnkChoosePinForMe")
	WebElement oChoosePinFor_Link;

	@FindBy(css = "#a-eca")
	WebElement oElectronicCommunicationsAgreement_Link;

	@FindBy(css = "#a-cha")
	WebElement oCardholderAgreement_Link;

	@FindBy(css = "#a-cha")
	WebElement oPrivacyPolicy_Link;

	@FindBy(css = "#Continue")
	WebElement oGetMyCard_Button;
	
	
	public void submitCustomerInfo(String registerCustomerType, String addressType)
	{
		selectAll(oFirstName_Input).sendKeys(Customer.FirstName);
		selectAll(oLastName_Input).sendKeys(Customer.LastName);
		selectAll(oPhoneMobile_Input).sendKeys(Customer.CellPhone);
		selectAll(oEmail_Input).sendKeys(Customer.Email);
		selectAll(oBirthDate_Input).sendKeys(Customer.DOB);
		selectAll(oCardPIN_Input).sendKeys(Customer.Pin);
		

		//Customer Type
		selectAll(oSocialSecurityNumber_Input).sendKeys(Customer.getGeneratedSSN(registerCustomerType));
		
		//Address Type
		Select stateSelect = new Select(oState_DropDown);
		selectAll(oAddress1_Input).sendKeys(Customer.addressMap.get(addressType)[1]);
		selectAll(oAddress2_Input).sendKeys(Customer.addressMap.get(addressType)[2]);
		selectAll(oCity_Input).sendKeys(Customer.addressMap.get(addressType)[3]);		
		stateSelect.selectByValue(Customer.addressMap.get(addressType)[4]);
		selectAll(oZip_Input).sendKeys(Customer.addressMap.get(addressType)[5]);		
		
	}
	
	public void submit()
	{
		oGetMyCard_Button.click();		
	}

}