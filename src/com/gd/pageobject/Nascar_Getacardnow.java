package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.browser.debug.Customer;

public class Nascar_Getacardnow extends PageObjectBase {

	public final static String url = "https://www.greendot.com/racing/getacardnow";

	@FindBy(css = "#FirstName")
	WebElement oFirstName_Input;

	@FindBy(css = "#LastName")
	WebElement oLastName_Input;

	@FindBy(css = "#Address")
	WebElement oAddress_Input;

	@FindBy(css = "#Apartment")
	WebElement oApartment_Input;
	
	@FindBy(css = "#State")
	WebElement oState_DropDown;

	@FindBy(css = "#City")
	WebElement oCity_Input;

	@FindBy(css = "#Zip")
	WebElement oZip_Input;

	@FindBy(css = "#Email")
	WebElement oEmail_Input;

	@FindBy(css = "#CellPhone")
	WebElement oCellPhone_Input;

	@FindBy(css = "#HomePhone")
	WebElement oHomePhone_Input;

	@FindBy(css = ".lock>a")
	WebElement oLearnMore_Link;

	@FindBy(css = "#gdssn-help")
	WebElement oGdssnHelp_Link;

	@FindBy(css = "#SocialSecurity")
	WebElement oSocialSecurity_Input;

	@FindBy(css = "#Birthday")
	WebElement oBirthday_Input;

	@FindBy(css = "#ddEligible-help")
	WebElement oDdeligibleHelp_Link;

	@FindBy(css = "#atmpin-help")
	WebElement oAtmpinHelp_Link;

	@FindBy(css = "#ATMPin")
	WebElement oATMPin_Input;

	@FindBy(css = "#lnkChoosePinForMe")
	WebElement oChoosePinFor_Link;

	@FindBy(css = "#btnEsign")
	WebElement oContinue_Button;

	
	public void submitCustomerInfo(String registerCustomerType, String addressType)
	{
		selectAll(oFirstName_Input).sendKeys(Customer.FirstName);
		selectAll(oLastName_Input).sendKeys(Customer.LastName);
		selectAll(oCellPhone_Input).sendKeys(Customer.CellPhone);
		selectAll(oHomePhone_Input).sendKeys(Customer.HomePhone);
		selectAll(oEmail_Input).sendKeys(Customer.Email);
		selectAll(oBirthday_Input).sendKeys(Customer.DOB);
		selectAll(oATMPin_Input).sendKeys(Customer.Pin);
		

		//Customer Type
		selectAll(oSocialSecurity_Input).sendKeys(Customer.getGeneratedSSN(registerCustomerType));
		
		//Address Type
		Select stateSelect = new Select(oState_DropDown);
		selectAll(oAddress_Input).sendKeys(Customer.addressMap.get(addressType)[1]);
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