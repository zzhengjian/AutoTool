package com.gd.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import com.browser.debug.Customer;

public class GD_Login {

	public final static String url = "https://www.greendot.com/greendot/login";	
	
	@FindBy(css = ".messages>p")
	List<WebElement> oErroMsg_Text;

	@FindBy(css = "#TxtAccountNoOrUserId")
	WebElement oTxtAccountNoOrUserId_Input;

	@FindBy(css = "[for='TxtPassword']")
	WebElement oPassword_Label;

	@FindBy(css = "#TxtPassword")
	WebElement oTxtPassword_Input;

	@FindBy(css = "[for='CheckBoxRememberUserId']")
	WebElement oRememberUserId_Label;

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
	
	public boolean loginError()
	{		
		return oErroMsg_Text.size() > 0;
			
	}
}