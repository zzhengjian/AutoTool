package com.gd.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.browser.debug.Customer;

public class Rush_Login {

	public final static String url = "https://www.rushcardlive.com/prepaid/login";

	@FindBy(css = ".messages>p")
	WebElement oErroMsg_Text;
	
	@FindBy(css = "#TxtAccountNoOrUserId")
	WebElement oTxtAccountNoOrUserId_Input;

	@FindBy(css = "#TxtPassword")
	WebElement oTxtPassword_Input;

	@FindBy(css = "#CheckBoxRememberUserId")
	WebElement oCheckBoxRememberUserId_CheckBox;

	@FindBy(css = "#btnLogin")
	WebElement oSignIn_Button;
	
	public void Login()
	{		
		oTxtAccountNoOrUserId_Input.sendKeys(Customer.UserId);
		oTxtPassword_Input.sendKeys(Customer.Password);
		oSignIn_Button.click();
	}
	
	public void Login(String userid, String Password)
	{
		
		oTxtAccountNoOrUserId_Input.sendKeys(userid);
		oTxtPassword_Input.sendKeys(Password);
		oSignIn_Button.click();
	}

}