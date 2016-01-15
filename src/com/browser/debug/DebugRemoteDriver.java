package com.browser.debug;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;



public class DebugRemoteDriver extends RemoteWebDriver {
	
	private CommandExecutor executor;
	private Capabilities capabilities;
	private String sessionId;
	private WebDriver oWebDirver;
	
	public static String[] WebDriverKeyword = {"goTo","back","forward","reload","getCurrentUrl","getTitle","getAlert","getAlertText","acceptAlert","dismissAlert","execute","getWindowHandle","getWindowHandles","switchToWindow"};
	
	public DebugRemoteDriver(URL remoteAddress, String sessionId )
	{
		setCommandExecutor(new HttpCommandExecutor(remoteAddress));
		setSessionId(sessionId);
	}
	
	public DebugRemoteDriver(WebDriver oWebDirver )
	{
		this.oWebDirver = oWebDirver;
	}
	
	public String sendCommand(String commandName, String ...sParam)
	{
		String sText = null;
		switch(commandName)
		{		
		
			case "goTo":
				oWebDirver.get(sParam[0]);
				break;
				
			case "back":
				oWebDirver.navigate().back();
				break;
				
			case "forward":
				oWebDirver.navigate().forward();
				break;
				
			case "reload":
				oWebDirver.navigate().refresh();
				break;
				
			case "getCurrentUrl":
				sText = oWebDirver.getCurrentUrl();
				break;
				
			case "getTitle":
				sText = oWebDirver.getTitle();
				break;	
				
			case "getAlert":
				oWebDirver.switchTo().alert();
				break;
				
			case "getAlertText":
				sText = oWebDirver.switchTo().alert().getText();
				break;			
			case "acceptAlert":
				oWebDirver.switchTo().alert().accept();
				break;
				
			case "dismissAlert":
				oWebDirver.switchTo().alert().dismiss();	
				break;
				
			case "execute":
				sText = (String)((RemoteWebDriver)oWebDirver).executeScript(sParam[0]);
				break;
				
			case "getWindowHandle":
				sText = oWebDirver.getWindowHandle();
				break;
				
			case "getWindowHandles":
				Set<String> lWindowHandles = oWebDirver.getWindowHandles();
				sText =  lWindowHandles.toString();
				break;
				
			case "switchToWindow":
				oWebDirver.switchTo().window(sParam[0]);
				sText = commandName;
				break;
				
			default: 
				break;					
		
		}
		
		return sText;
	}
	
}
