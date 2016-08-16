package com.browser.debug;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;



public class DebugRemoteDriver extends RemoteWebDriver {
	
	private WebDriver oWebDirver;
	
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
		
		switch(Enum.valueOf(Commands.class, commandName))
		{		
		
			case goTo:
				oWebDirver.get(sParam[0]);
				break;
				
			case back:
				oWebDirver.navigate().back();
				break;
				
			case forward:
				oWebDirver.navigate().forward();
				break;
				
			case reload:
				oWebDirver.navigate().refresh();
				break;
				
			case getCurrentUrl:
				sText = oWebDirver.getCurrentUrl();
				break;
				
			case getTitle:
				sText = oWebDirver.getTitle();
				break;	
				
			case getAlert:
				oWebDirver.switchTo().alert();
				break;
				
			case getAlertText:
				sText = oWebDirver.switchTo().alert().getText();
				break;			
			case acceptAlert:
				oWebDirver.switchTo().alert().accept();
				break;
				
			case dismissAlert:
				oWebDirver.switchTo().alert().dismiss();	
				break;
				
			case execute:
				sText = (String)((RemoteWebDriver)oWebDirver).executeScript(sParam[0]);
				break;
				
			case getWindowHandle:
				sText = oWebDirver.getWindowHandle();
				break;
				
			case getWindowHandles:
				Set<String> lWindowHandles = oWebDirver.getWindowHandles();
				sText =  lWindowHandles.toString();
				break;
				
			case switchToWindow:
				oWebDirver.switchTo().window(sParam[0]);
				sText = commandName;
				break;
				
			case switchToDefaultContent:
				oWebDirver.switchTo().defaultContent();
				sText = commandName;
				break;
				
			case switchToFrame:
				WebElement frameElement = oWebDirver.findElement(Utils.getBy(sParam[0]));
				oWebDirver.switchTo().frame(frameElement);
				sText = commandName;
				break;
				
			default: 
				break;					
		
		}
		
		return sText;
	}
	
}
