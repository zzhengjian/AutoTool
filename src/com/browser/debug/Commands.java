package com.browser.debug;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class Commands {
	public WebDriver driver;
	//public final static String[] All = getAllCommands();
	
	public final static String Click = "click";
	public final static String Clear = "clear";
	public final static String GetCurrentUrl = "getCurrentUrl";
	public final static String GO_BACK = "goBack";
	public final static String GO_FORWARD = "goForward";
	public final static String REFRESH = "refresh";
	public final static String GET_CURRENT_WINDOW_HANDLE = "getCurrentWindowHandle";
	public final static String GET_WINDOW_HANDLES = "getWindowHandles";
	public final static String SWITCH_TO_WINDOW = "switchToWindow";
	public final static String GET_ELEMENT_TEXT = "getElementText";
	public final static String GET_ELEMENT_TAG_NAME = "getElementTagName";
	public final static String IS_ELEMENT_SELECTED = "isElementSelected";
	public final static String IS_ELEMENT_ENABLED = "isElementEnabled";
	public final static String IS_ELEMENT_DISPLAYED = "isElementDisplayed";	
	public final static String GET_ELEMENT_ATTRIBUTE = "getElementAttribute";
	public final static String GET_CSS_PROPERTY = "getCssProperty";
	
	
	private ArrayList<String> allCommands = new ArrayList<String>();
	
	public String[] getAllCommands()
	{
		allCommands.add(Click);
		allCommands.add(Clear);
		allCommands.add(GetCurrentUrl);
		allCommands.add(GET_ELEMENT_TEXT);
		allCommands.add(GET_ELEMENT_TAG_NAME);
		allCommands.add(IS_ELEMENT_SELECTED);	
		allCommands.add(IS_ELEMENT_ENABLED);	
		allCommands.add(IS_ELEMENT_DISPLAYED);
		allCommands.add(GET_ELEMENT_ATTRIBUTE);	
		allCommands.add(GET_CSS_PROPERTY);	
		allCommands.add(GO_BACK);
		allCommands.add(GO_FORWARD);		

		return allCommands.toArray(new String[allCommands.size()]);
	}

}
