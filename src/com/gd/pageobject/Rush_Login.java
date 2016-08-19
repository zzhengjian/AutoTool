package com.gd.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.browser.debug.Customer;

public class Rush_Login {

	public final static String url = "https://www.rushcardlive.com/prepaid/login";

	@FindBy(css = ".messages>p")
	List<WebElement> oErroMsg_Text;
	
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
	
	public boolean loginError()
	{		
		return oErroMsg_Text.size() > 0;
			
	}

}