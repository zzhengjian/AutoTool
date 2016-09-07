package com.gd.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gd.driver.Customer;

public class WMC_Login {

	public final static String url = "https://www.walmartmoneycard.com/login";

	@FindBy(css = "#div-form-error-message")
	List<WebElement> oFormErrorMsg_Text;
	
	@FindBy(css = "#TxtAccountNoOrUserId")
	WebElement oTxtAccountNoOrUserId_Input;

	@FindBy(css = "#TxtPassword")
	WebElement oTxtPassword_Input;

	@FindBy(css = "#p-forgot-userid>a")
	WebElement oForgotUserId_Link;

	@FindBy(css = "#p-forgot-password>a")
	WebElement oForgotPassword_Link;

	@FindBy(css = "#btn-submit")
	WebElement oLogIn_Button;

	public void Login()
	{		
		oTxtAccountNoOrUserId_Input.sendKeys(Customer.UserId);
		oTxtPassword_Input.sendKeys(Customer.Password);
		oLogIn_Button.click();
	}
	
	public void Login(String userid, String Password)
	{
		
		oTxtAccountNoOrUserId_Input.sendKeys(userid);
		oTxtPassword_Input.sendKeys(Password);
		oLogIn_Button.click();
	}
	
	public boolean loginError()
	{
		return oFormErrorMsg_Text.size() > 0 && oFormErrorMsg_Text.get(0).isDisplayed();		
	}
	
}