package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.browser.debug.Customer;

public class Nascar_Login {

	public final static String url = "https://www.greendot.com/racing/login";

	@FindBy(css = ".messages>p")
	WebElement oErroMsg_Text;
	
	@FindBy(css = "#TxtAccountNoOrUserId")
	WebElement oTxtAccountNoOrUserId_Input;

	@FindBy(css = "#popAcctNo")
	WebElement oPopacctno_Link;

	@FindBy(css = "#TxtPassword")
	WebElement oTxtPassword_Input;

	@FindBy(css = "#tooltip-password")
	WebElement oTooltipPassword_Link;

	@FindBy(css = "#CheckBoxRememberUserId")
	WebElement oCheckBoxRememberUserId_CheckBox;

	@FindBy(css = "#btnLogin")
	WebElement oBtnlogin_Input;
	
	public void Login()
	{		
		oTxtAccountNoOrUserId_Input.sendKeys(Customer.UserId);
		oTxtPassword_Input.sendKeys(Customer.Password);
		oBtnlogin_Input.click();
	}
	
	public void Login(String userid, String Password)
	{
		
		oTxtAccountNoOrUserId_Input.sendKeys(userid);
		oTxtPassword_Input.sendKeys(Password);
		oBtnlogin_Input.click();
	}

}