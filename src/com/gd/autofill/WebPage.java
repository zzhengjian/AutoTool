package com.gd.autofill;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gd.driver.Customer;

public class WebPage {
	
	private List<WebElement> avaliableInputs = new ArrayList<WebElement>();
	private String[] currentAddresses;
	private WebElement oWebElement;
	
	
	public String[] getAddresses() {
		return currentAddresses;
	}

	public void setAddresses(String[] addresses) {
		this.currentAddresses = addresses;
	}

	public void getAllInputs(WebDriver oWebDriver)
	{
		List<WebElement> allInputs = oWebDriver.findElements(By.xpath(".//input[not(@type='radio')][not(@type='hidden')][not(@type='checkbox')][not(@type='image')]|.//select"));
		int i = 0;
		for(WebElement input : allInputs)
		{
			if(input.isDisplayed() && input.isEnabled())
				avaliableInputs.add(input);			
			System.out.println(i++);
		}
		
	}
	
	public void fill(WebDriver oWebDriver)
	{
		getAllInputs(oWebDriver);
		
		for(WebElement input : avaliableInputs)
		{				
			fillElement(input);			
		}		
		
	}
	
	
	private void fillElement(WebElement e)
	{
		String fieldType = "";
		String id = e.getAttribute("id").toLowerCase();
		String name = e.getAttribute("name").toLowerCase();

		if(id.contains("firstname"))
		{
			
			type(Customer.FirstName);
		}
		else if(id.contains("lastname"))
		{
			type(Customer.LastName);			
		}
		
		else if(id.contains("socialsecurity") || id.contains("ssn"))
		{
			type(Customer.SSN);			
		}
		
		else if(id.contains("birthday") || id.contains("birthdate") || id.contains("dateofbirth"))
		{
			type(Customer.DOB);			
		}
		else if(id.contains("cellphone") || id.contains("mobile"))
		{
			type(Customer.CellPhone);	
			
		}
		else if(id.contains("homephone"))
		{			
			type(Customer.HomePhone);	
		}
		else if(id.contains("email"))
		{
			type(Customer.Email);
		}
		
		//Addresses
		else if(id.toLowerCase().equals("address") || !id.equals("address2") && !id.contains("email"))
		{
			type(currentAddresses[1]);
		}
		
		else if(id.contains("apartment") || id.equals("address2"))
		{
			type(currentAddresses[2]);
		}
		else if(id.contains("state"))
		{
			type(currentAddresses[3]);
		}		
		else if(id.contains("city"))
		{
			type(currentAddresses[4]);
		}
		else if(id.contains("zip"))
		{
			type(currentAddresses[5]);
		}		
		
		else if(id.contains("userid"))
		{
			type("");			
		}
		
		else if(id.contains("password"))
		{
			type("NECTESTAU0");
		}		
		
		else if(id.contains("atmpin") || id.contains("cardpin"))
		{
			type(Customer.Pin);
		}		
		
	}

	
	public void type(String text)
	
	{
		if(oWebElement.getTagName().equalsIgnoreCase("select"))
		{
			new Select(oWebElement).selectByValue(text);	
			return;
		}
		if(!oWebElement.getAttribute("type").equals("date"))
		{
			this.oWebElement.clear();
		}		
		this.oWebElement.sendKeys(text);
		
	}
}
