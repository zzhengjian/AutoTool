package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GDFlex_SendMyCard {

	public final static String url = "https://www.greendot.com/signup/send-my-card";

	@FindBy(css = ".section-heading--dotted")
	WebElement oPersonalInformation_h2_Title;

	@FindBy(css = ".section-heading--dotted>span")
	WebElement oPersonalInformation_Text;

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

	@FindBy(css = ".input--state>div>label>span")
	WebElement oState_Text;

	@FindBy(css = "#State")
	WebElement oState_DropDown;

	@FindBy(css = "[for='Zip']>span")
	WebElement oZipCode_Text;

	@FindBy(css = "#Zip")
	WebElement oZip_Input;

	@FindBy(css = "[for='Phone']>span")
	WebElement oPhoneNumber_Text;

	@FindBy(css = "#Phone")
	WebElement oPhone_Input;

	@FindBy(css = "#IsMobilePhone")
	WebElement oIsMobilePhone_RadioButton;

	@FindBy(css = ".input-container--phone-type>div:nth-of-type(1)>div>label")
	WebElement oMobilePhone_Label;

	@FindBy(css = "#IsMobilePhone")
	WebElement oIsMobilePhone_RadioButton_1;

	@FindBy(css = ".input-container--phone-type>div:nth-of-type(2)>div>label")
	WebElement oHomePhone_Label;

	@FindBy(css = "[for='Email']>span")
	WebElement oEmail_Text;

	@FindBy(css = "#Email")
	WebElement oEmail_Input;

	@FindBy(css = "[for='SSN']>span")
	WebElement oSocialSecurityNumber_Text;

	@FindBy(css = "#SSN")
	WebElement oSsn_Input;

	@FindBy(css = ".page2-js>section:nth-of-type(1)>div:nth-of-type(8)>div:nth-of-type(2)>a")
	WebElement oInputHelpLink_Link;

	@FindBy(css = ".page2-js>section:nth-of-type(1)>div:nth-of-type(8)>div:nth-of-type(2)>a>i")
	WebElement oIconHelpInput_Icon;

	@FindBy(css = "[for='DateOfBirth']>span")
	WebElement oDateOfBirth_Text;

	@FindBy(css = "#DateOfBirth")
	WebElement oDateOfBirth_Input;

	@FindBy(css = "[for='AtmPin']>span")
	WebElement oSet4Digit_Text;

	@FindBy(css = "#AtmPin")
	WebElement oAtmPin_Input;

	@FindBy(css = ".page2-js>section:nth-of-type(1)>div:nth-of-type(10)>div>div:nth-of-type(2)>a")
	WebElement oInputHelpLink_Link_1;

	@FindBy(css = ".page2-js>section:nth-of-type(1)>div:nth-of-type(10)>div>div:nth-of-type(2)>a>i")
	WebElement oIconHelpInput_Icon_1;

	@FindBy(css = "#choosePinForMeLink")
	WebElement oChooseSecurePin_Label;

	@FindBy(css = "#IsAgreedToPromoEmails")
	WebElement oIsAgreedToPromoEmails_CheckBox;

	@FindBy(css = "[for='IsAgreedToPromoEmails']>span")
	WebElement oIDLike_Text;

	@FindBy(css = "h5")
	WebElement oSendMeDaily_h5_Title;

	@FindBy(css = ".double-pad-bottom.triple-gap-top.triple-gap-top>div:nth-of-type(2)>p")
	WebElement oCarrierMessageAnd_Text;

	@FindBy(css = ".double-pad-bottom.triple-gap-top.triple-gap-top>div:nth-of-type(2)>p>a")
	WebElement oAccountAlertsTerms_Link;

	@FindBy(css = "#IsAgreedToEmailAlerts")
	WebElement oIsAgreedToEmailAlerts_CheckBox;

	@FindBy(css = "[for='IsAgreedToEmailAlerts']>span")
	WebElement oEmailMe_Text;

	@FindBy(css = "#IsAgreedToTextAlerts")
	WebElement oIsAgreedToTextAlerts_CheckBox;

	@FindBy(css = "[for='IsAgreedToTextAlerts']>span")
	WebElement oTextMe_Text;

	@FindBy(css = "#IsAgreedToECA")
	WebElement oIsAgreedToECA_CheckBox;

	@FindBy(css = "[for='IsAgreedToECA']>span")
	WebElement oIHaveRead_Text;

	@FindBy(css = "[for='IsAgreedToECA']>a")
	WebElement oElectronicCommunicationsAgreement_Link;

	@FindBy(css = ".double-pad-bottom.triple-gap-top.triple-gap-top>div:nth-of-type(3)>p")
	WebElement oThisAgreementStates_Text;

	@FindBy(css = ".double-gap-top.gap-bottom.gap-bottom")
	WebElement oByClickingContinue_Text;

	@FindBy(css = ".double-gap-top.gap-bottom.gap-bottom>a")
	WebElement oPrivacyPolicy_Link;

	@FindBy(css = "#submit-button")
	WebElement oContinue_Button;

	@FindBy(css = ".align-center")
	WebElement oImportantInformationAbout_Text;

}