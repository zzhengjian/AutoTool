package com.gd.pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PageObjectBase {
	
	
	
	protected WebElement selectAll(WebElement element)	
	{
		element.sendKeys(Keys.CONTROL, "a");
		return element;
	}
	
}
