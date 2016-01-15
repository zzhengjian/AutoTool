package com.browser.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.browser.debug.Customer;
import com.browser.debug.Utils;

public class PageElements {
	
	private WebDriver oWebDriver;
	private WebElement oWebElement;
	
	public PageElements(WebDriver oWebDriver)
	{
		this.oWebDriver = oWebDriver;
		oWebDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	
	public void fillCustomerInfo(String sUrl, String customerType, String sAddressType) throws Exception{
		
		Customer oCustomer = new Customer();
		oCustomer.getSSN(customerType);
		String[] sAddress = null;
		
		if(sUrl.contains("www.greendot.com/greendot/getacardnow"))
		{
			//GD getacardnow
			
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#CellPhone").sendKeys(Customer.CellPhone);
			find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SocialSecurity").sendKeys(Customer.SSN);
			find("#Birthday").sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);
			find("#ATMPin").sendKeys(Customer.Pin);
			//Address
			
			sAddress = oCustomer.getAddress(sAddressType);
			find("#Address").sendKeys(sAddress[1]);
			find("#Apartment").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#State").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#Zip").sendKeys(sAddress[5]);	
		}		
		
		else if(sUrl.contains("www.greendot.com/greendot/activation/online-activation-init"))
		{
			//GD Activation
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#PhoneNumber").sendKeys(Customer.CellPhone);
			//find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SSN").sendKeys(Customer.SSN);
			find("#DateOfBirth").sendKeys(Customer.DOB);
			find("#EmailAddress").sendKeys(Customer.Email);		
			find("#ConfirmEmailAddress").sendKeys(Customer.Email);	
			find("#ATMPin").sendKeys(Customer.Pin);
			//Address		
			sAddress = oCustomer.getAddress(sAddressType);
			find("#StreetAddress").sendKeys(sAddress[1]);
			find("#ApartmentNumber").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#StateList").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#ZipCode").sendKeys(sAddress[5]);		
		}

		else if(sUrl.contains("www.greendot.com/greendot/account/add-second-card"))
		{
			//GD Add Second Card
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#ContactNumber").sendKeys(Customer.CellPhone);
			//find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SSN").sendKeys(Customer.SSN);
			find("#Birthday").sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);		
			//find("#ConfirmEmailAddress").sendKeys(Customer.Email);	
			//find("#ATMPin").sendKeys(Customer.Pin);
			//Address		
			sAddress = oCustomer.getAddress(sAddressType);
			find("#ResidentialAddressPart1").sendKeys(sAddress[1]);
			find("#ResidentialAddressPart2").sendKeys(sAddress[2]);
			find("#ResidentialCity").sendKeys(sAddress[3]);
			new Select(find("#ResidentialState").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#ResidentialZipCode").sendKeys(sAddress[5]);		
		}
		else if(sUrl.contains("www.greendot.com/register/personal-info"))
		{
			//GD Activation
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#Phone").sendKeys(Customer.CellPhone);
			//find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SSN").sendKeys(Customer.SSN);
			find("#DateOfBirth").sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);		
			find("#AtmPin").sendKeys(Customer.Pin);
			//Address		
			sAddress = oCustomer.getAddress(sAddressType);
			find("#Street").sendKeys(sAddress[1]);
			find("#Apartment").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#State").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#Zip").sendKeys(sAddress[5]);				
		}
		
		else if(sUrl.contains("www.walmartmoneycard.com/getacardnow"))
		{
			//walmart getacardnow
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#PhoneMobile").sendKeys(Customer.CellPhone);
			//find("#PhoneHome").sendKeys(Customer.HomePhone);
			find("#SocialSecurityNumber").sendKeys(Customer.SSN);
			find("input[id^='BirthDate']").sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);	
			find("#CardPIN").sendKeys(Customer.Pin);
			//Address
			sAddress = oCustomer.getAddress(sAddressType);
			find("#Address1").sendKeys(sAddress[1]);
			find("#Address2").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#State").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#ZIP").sendKeys(sAddress[5]);		
		}
		
		else if(sUrl.contains("www.walmartmoneycard.com/activation/online-activation-init"))
		{
			//walmart Activation
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#PhoneMobile").sendKeys(Customer.CellPhone);
			find("#PhoneHome").sendKeys(Customer.HomePhone);
			find("#SocialSecurityNumber").sendKeys(Customer.SSN);
			find("input[id^='BirthDate']").oWebElement.sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);	
			find("#CardPIN").sendKeys(Customer.Pin);
			//Address
			sAddress = oCustomer.getAddress(sAddressType);
			find("#Address1").sendKeys(sAddress[1]);
			find("#Address2").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#State").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#ZIP").sendKeys(sAddress[5]);		
		}

		else if(sUrl.contains("https://www.walmartmoneycard.com/account/add-second-card"))
		{
			//walmart getacardnow
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#ContactNumber").sendKeys(Customer.CellPhone);
			//find("#PhoneHome").sendKeys(Customer.HomePhone);
			find("#SSN").sendKeys(Customer.SSN);
			find("input[id^='BirthDate']").sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);	
			//Address
			sAddress = oCustomer.getAddress(sAddressType);
			find("#ResidentialAddressPart1").sendKeys(sAddress[1]);
			find("#ResidentialAddressPart2").sendKeys(sAddress[2]);
			find("#ResidentialCity").sendKeys(sAddress[3]);
			new Select(find("#ResidentialState").oWebElement).selectByValue(sAddress[4]);
			find("#ResidentialZipCode").sendKeys(sAddress[5]);		
		}
		
		else if(sUrl.contains("pos.greendot.com/registration/apply"))
		{
			//walmart Activation
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#MobilePhone").sendKeys(Customer.CellPhone);
			find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SSN").sendKeys(Customer.SSN);
			find("#DoB").sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);	
			new Select(find("#IdType").oWebElement).selectByVisibleText("Driver's License");
			find("#IdNumber").sendKeys(Utils.getRandomNumeric(8));
			find("#IdExpirationDate").sendKeys("01012018");
			//Wait<WebDriver> wait = new WebDriverWait(oWebDriver, 10);
//			WebDriverWait oWait = new WebDriverWait(oWebDriver, 10);
//			oWait.until(
//					ExpectedConditions.presenceOfElementLocated(By.cssSelector("#IdIssuer option[value=CA]"))
//					);		
			find("#IdIssuer option[value=CA]").oWebElement.click();
			//new Select(find("#IdIssuer").oWebElement).selectByValue("CA");			
			
			//Address
			sAddress = oCustomer.getAddress(sAddressType);
			find("#Address1").sendKeys(sAddress[1]);
			find("#Address2").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#State").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#ZipCode").sendKeys(sAddress[5]);		
		}
		
		else if(sUrl.contains("www.greendot.com/racing/activation/online-activation-init"))
		{
			//Nascar Activation
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#MobilePhone").sendKeys(Customer.CellPhone);
			find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SSN").sendKeys(Customer.SSN);
			find("#DateOfBirth").sendKeys(Customer.DOB);
			find("#EmailAddress").sendKeys(Customer.Email);	
			find("#ATMPin").sendKeys(Customer.Pin);
			//Address
			sAddress = oCustomer.getAddress(sAddressType);
			find("#StreetAddress").sendKeys(sAddress[1]);
			find("#ApartmentNumber").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#StateList").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#ZipCode").sendKeys(sAddress[5]);		
		}
		
		else if(sUrl.contains("www.greendot.com/racing/getacardnow"))
		{
			//Nascar OS
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#CellPhone").sendKeys(Customer.CellPhone);
			find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SocialSecurity").sendKeys(Customer.SSN);
			find("#Birthday").sendKeys(Customer.DOB);
			find("#Email").sendKeys(Customer.Email);	
			find("#ATMPin").sendKeys(Customer.Pin);
			//Address
			sAddress = oCustomer.getAddress(sAddressType);
			find("#Address").sendKeys(sAddress[1]);
			find("#Apartment").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#State").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#Zip").sendKeys(sAddress[5]);		
		}
		
		else if(sUrl.contains("www.rushcardlive.com/prepaid/activation/online-activation-init"))
		{
			//Rush Activation
			find("#FirstName").sendKeys(Customer.FirstName);
			find("#LastName").sendKeys(Customer.LastName);
			find("#MobilePhone").sendKeys(Customer.CellPhone);
			find("#HomePhone").sendKeys(Customer.HomePhone);
			find("#SSN").sendKeys(Customer.SSN);
			find("#DateOfBirth").sendKeys(Customer.DOB);
			find("#EmailAddress").sendKeys(Customer.Email);	
			find("#ATMPin").sendKeys(Customer.Pin);
			//Address
			sAddress = oCustomer.getAddress(sAddressType);
			find("#StreetAddress").sendKeys(sAddress[1]);
			find("#ApartmentNumber").sendKeys(sAddress[2]);
			find("#City").sendKeys(sAddress[3]);
			new Select(find("#StateList").oWebElement).selectByValue(sAddress[4]);
			//find("#State").sendKeys(Customer.Pin);
			find("#ZipCode").sendKeys(sAddress[5]);		
		}
		
		else
			throw new Exception("page not impletemented");
		
	}
	
	
	
	private PageElements find(String sSelector){
		
		By oBy = Utils.getBy(sSelector);
		
		oWebElement = oWebDriver.findElement(oBy);
		return this;
		
		
	}
	
	private void sendKeys(String s)
	{
		if(!"date".equals(oWebElement.getAttribute("type")))
			oWebElement.sendKeys(Keys.CONTROL + "a");
		oWebElement.sendKeys(s);
	}
	
}
