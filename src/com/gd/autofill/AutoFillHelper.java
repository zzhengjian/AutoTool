package com.gd.autofill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.gd.pageobject.GDFlex_1199seiu;
import com.gd.pageobject.GDFlex_PersonalInfo;
import com.gd.pageobject.GDFlex_SendMyCard;
import com.gd.pageobject.GD_AddSecondCard;
import com.gd.pageobject.Nascar_AddSecondCard;
import com.gd.pageobject.Nascar_Getacardnow;
import com.gd.pageobject.Nascar_OnlineActivationInit;
import com.gd.pageobject.Rush_OnlineActivationInit;
import com.gd.pageobject.WMC_AddSecondCard;
import com.gd.pageobject.WMC_Getacardnow;
import com.gd.pageobject.WMC_OnlineActivationInit;

public class AutoFillHelper {
	
	public static void fill(WebDriver driver, String registerCustomerType, String addressType)
	{
		
		if(driver.getCurrentUrl().equals(GDFlex_1199seiu.url))
		{
			GDFlex_1199seiu oGDFlex_1199seiu = PageFactory.initElements(driver, GDFlex_1199seiu.class);		
			oGDFlex_1199seiu.submitCustomerInfo(registerCustomerType, addressType);
			
		}		
		else if(driver.getCurrentUrl().equals(GDFlex_PersonalInfo.url))
		{
			GDFlex_PersonalInfo oGDFlex_PersonalInfo = PageFactory.initElements(driver, GDFlex_PersonalInfo.class);		
			oGDFlex_PersonalInfo.submitCustomerInfo(registerCustomerType, addressType);			
		}		
		else if(driver.getCurrentUrl().contains(GDFlex_SendMyCard.url))
		{
			GDFlex_SendMyCard oGDFlex_SendMyCard = PageFactory.initElements(driver, GDFlex_SendMyCard.class);		
			oGDFlex_SendMyCard.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(GD_AddSecondCard.url))
		{
			GD_AddSecondCard oGD_AddSecondCard = PageFactory.initElements(driver, GD_AddSecondCard.class);		
			oGD_AddSecondCard.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(Nascar_Getacardnow.url))
		{
			Nascar_Getacardnow oNascar_Getacardnow = PageFactory.initElements(driver, Nascar_Getacardnow.class);		
			oNascar_Getacardnow.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(Nascar_OnlineActivationInit.url))
		{
			Nascar_OnlineActivationInit oNascar_OnlineActivationInit = PageFactory.initElements(driver, Nascar_OnlineActivationInit.class);		
			oNascar_OnlineActivationInit.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(Nascar_AddSecondCard.url))
		{
			Nascar_AddSecondCard oNascar_AddSecondCard = PageFactory.initElements(driver, Nascar_AddSecondCard.class);		
			oNascar_AddSecondCard.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(Rush_OnlineActivationInit.url))
		{
			Rush_OnlineActivationInit oRush_OnlineActivationInit = PageFactory.initElements(driver, Rush_OnlineActivationInit.class);		
			oRush_OnlineActivationInit.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(WMC_AddSecondCard.url))
		{
			WMC_AddSecondCard oWMC_AddSecondCard = PageFactory.initElements(driver, WMC_AddSecondCard.class);		
			oWMC_AddSecondCard.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(WMC_Getacardnow.url))
		{
			WMC_Getacardnow oWMC_Getacardnow = PageFactory.initElements(driver, WMC_Getacardnow.class);		
			oWMC_Getacardnow.submitCustomerInfo(registerCustomerType, addressType);			
		}
		else if(driver.getCurrentUrl().equals(WMC_OnlineActivationInit.url))
		{
			WMC_OnlineActivationInit oWMC_OnlineActivationInit = PageFactory.initElements(driver, WMC_OnlineActivationInit.class);		
			oWMC_OnlineActivationInit.submitCustomerInfo(registerCustomerType, addressType);			
		}
		
	}

}
