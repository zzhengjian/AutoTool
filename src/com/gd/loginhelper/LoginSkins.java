package com.gd.loginhelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.browser.debug.Customer;
import com.gd.pageobject.GD_Challengelogin;
import com.gd.pageobject.GD_Login;
import com.gd.pageobject.Nascar_Challengelogin;
import com.gd.pageobject.Nascar_Login;
import com.gd.pageobject.Rush_Challengelogin;
import com.gd.pageobject.Rush_Login;
import com.gd.pageobject.WMC_Challengelogin;
import com.gd.pageobject.WMC_Login;

public class LoginSkins {
	
	
		
	
	public static void login(WebDriver driver, String Project)
	{
		
		if(Project.equalsIgnoreCase("GreenDot"))
		{
			driver.get(GD_Login.url);	
			GD_Login loginPage = PageFactory.initElements(driver, GD_Login.class);		
			loginPage.Login(Customer.UserId, Customer.Password);
			
			GD_Challengelogin chanllengePage = PageFactory.initElements(driver, GD_Challengelogin.class);
			chanllengePage.submitChallengeAnswer();
			
		}
			
		else if(Project.equalsIgnoreCase("Nascar"))
		{
			driver.get(Nascar_Login.url);	
			Nascar_Login loginPage = PageFactory.initElements(driver, Nascar_Login.class);		
			loginPage.Login(Customer.UserId, Customer.Password);
			
			Nascar_Challengelogin chanllengePage = PageFactory.initElements(driver, Nascar_Challengelogin.class);
			chanllengePage.submitChallengeAnswer();
		}
			
		else if(Project.equalsIgnoreCase("Rush"))
		{
			driver.get(Rush_Login.url);	
			Rush_Login loginPage = PageFactory.initElements(driver, Rush_Login.class);		
			loginPage.Login(Customer.UserId, Customer.Password);
			
			Rush_Challengelogin chanllengePage = PageFactory.initElements(driver, Rush_Challengelogin.class);
			chanllengePage.submitChallengeAnswer();
		}
			
		else if(Project.equalsIgnoreCase("Walmart"))
		{
			driver.get(WMC_Login.url);	
			WMC_Login loginPage = PageFactory.initElements(driver, WMC_Login.class);		
			loginPage.Login(Customer.UserId, Customer.Password);
			
			WMC_Challengelogin chanllengePage = PageFactory.initElements(driver, WMC_Challengelogin.class);
			chanllengePage.submitChallengeAnswer();
		}
	}
}
