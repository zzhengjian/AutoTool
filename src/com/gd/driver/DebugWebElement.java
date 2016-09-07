package com.gd.driver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import com.gd.common.Property;
import com.gd.common.Utils;
import com.gd.pogen.ElementHelper;
import com.google.common.collect.ImmutableMap;



public class DebugWebElement extends RemoteWebElement {
	
	private WebDriver oWebDriver;
	private JavascriptExecutor oJavascriptExecutor;
	public WebElement oWebElement;
	private String sElementTag;
	private By oBy;
	private String outline = "";
	private String background = "";
	private Actions oAction;
	
	public DebugWebElement(String sTag, WebDriver oDriver)
	{
		this.oWebDriver = oDriver;
		this.oJavascriptExecutor = (JavascriptExecutor)oDriver;
		this.sElementTag = sTag;		
		this.oBy = Utils.getBy(sTag);
		this.oAction = new Actions(oWebDriver);
		this.oWebElement = oWebDriver.findElement(oBy);
	}
	
	public DebugWebElement(String sTag, WebDriver oDriver, String frameName)
	{
		this.oWebDriver = oDriver;
		this.oJavascriptExecutor = (JavascriptExecutor)oDriver;
		this.sElementTag = sTag;		
		this.oBy = Utils.getBy(sTag);
		//this.oAction = new Actions(oWebDriver);
		oWebDriver.switchTo().frame(frameName);
		this.oWebElement = oWebDriver.findElement(oBy);
	}


	
	/**
	 * Highlight element by drawing red border around it.
	 */
	public void highlightMe()
	{		
		//((RemoteWebElement)oWebElement).getCoordinates();
		if(oWebElement==null)
		{
			throw new NoSuchElementException("No element found");
		}
		((RemoteWebElement)oWebElement).getCoordinates().inViewPort();
		this.outline = (String)oJavascriptExecutor.executeScript("var outline = arguments[0].style.outline; arguments[0].style.outline='3px solid red'; return outline;", oWebElement);
		this.background = (String)oJavascriptExecutor.executeScript("var outline = arguments[0].style.background; arguments[0].style.background ='yellow'; return outline;", oWebElement);
		
		Utils.sleepFor(1);
		unHighlightMe();
		Utils.sleepFor(1);
		oJavascriptExecutor.executeScript("return arguments[0].style.outline='3px solid red';", oWebElement);	
		oJavascriptExecutor.executeScript("return arguments[0].style.background ='yellow';", oWebElement);
		Utils.sleepFor(1);
		unHighlightMe();
		Utils.sleepFor(1);
		oJavascriptExecutor.executeScript("return arguments[0].style.outline='3px solid red';", oWebElement);	
		oJavascriptExecutor.executeScript("return arguments[0].style.background ='yellow';", oWebElement);
		Utils.sleepFor(1);
		unHighlightMe();
	}
	
	/**
	 * Unhighlight element by removing red border around it.
	 */	
	public void unHighlightMe()
	{
		if (outline == null)
		{
			oJavascriptExecutor.executeScript("arguments[0].style=''", oWebElement);
			oJavascriptExecutor.executeScript("arguments[0].background=''", oWebElement);
		}
		else
		{
			oJavascriptExecutor.executeScript("arguments[0].style.outline='" + outline + "'" , oWebElement);
			oJavascriptExecutor.executeScript("arguments[0].style.background='" + background + "'" , oWebElement);
		}
	}	
	
	public String sendCommand(String sCommand, String ...param)
	{
		String sText = null;
		ElementHelper elementHelper;
		switch(Enum.valueOf(Commands.class, sCommand))
		{
		case click:
			if(param.length>0&&!"".equals(param[0]))
			{
				
				oAction.moveToElement(oWebElement, Integer.parseInt(param[0].split(",")[0]), Integer.parseInt(param[0].split(",")[1])).perform();
				oAction.click().perform();
				break;	
			}
			else
			{
				oWebElement.click();
				break;		
			}
			
		case clear:
			oWebElement.clear();
			break;
			
		case sendKeys:
			oWebElement.sendKeys(param[0]);
			break;
			
		case getText:
			sText = oWebElement.getText();			 
			break;

		case getTagName:
			sText = oWebElement.getTagName();			 
			break;
			
		case mouseover:
			oAction.moveToElement(oWebElement).perform();			 
			break;
			
		case isDisplayed:
			sText = String.valueOf(oWebElement.isDisplayed());			
			sText = "Element isdisplayed: " + sText;
			break;
			
		case isEnabled:
			sText = String.valueOf(oWebElement.isEnabled());
			sText = "Element isEnabled: " + sText;
			break;
			
		case isSelected:
			sText = String.valueOf(oWebElement.isSelected());
			sText = "Element isSelected: " + sText;
			break;
			
		case getCssValue:
			sText = oWebElement.getCssValue(param[0]);
			break;
			
		case getAttribute:
			sText = oWebElement.getAttribute(param[0]);
			break;
			
		case executeOnElement:
			sText = (String) oJavascriptExecutor.executeScript(param[0], oWebElement);
			break;
			
		case getElementInfo:
			StringBuilder elementinfo = new StringBuilder();
			elementinfo = elementinfo.append("font-size: ").append(oWebElement.getCssValue("font-size")).append("\n");
			elementinfo = elementinfo.append("font-family: ").append(oWebElement.getCssValue("font-family")).append("\n");
			elementinfo = elementinfo.append("color: ").append(oWebElement.getCssValue("color")).append("\n");
			elementinfo = elementinfo.append("background-color: ").append(oWebElement.getCssValue("background-color")).append("\n");
			elementinfo = elementinfo.append("src: ").append(oWebElement.getAttribute("src")).append("\n");
			elementinfo = elementinfo.append("href: ").append(oWebElement.getAttribute("href")).append("\n");
			elementinfo = elementinfo.append("text: ").append(oWebElement.getText()).append("\n");
			sText = elementinfo.toString();
			break;
			
		case addNewElement:
			elementHelper = new ElementHelper(oWebElement);
			StringBuilder gdelement = new StringBuilder();
			
			gdelement.append(AutoTool.PageName);
			gdelement.append(".addElement(\"").append(elementHelper.evaluateElementName()).append(elementHelper.elementNameExt()).append("\",").append("\n");
			gdelement.append("	GdElement.new(").append("\n");
			if(sElementTag.startsWith("/")||sElementTag.startsWith("./"))
				gdelement.append("		:desktopxpath => \"").append(sElementTag).append("\"").append("\n");
			else
				gdelement.append("		:desktopcss => \"").append(sElementTag).append("\"").append("\n");
			gdelement.append("		)").append("\n");
			gdelement.append("	)").append("\n");
			
			sText = elementHelper.addNewGDElement(AutoTool.PageName, sElementTag);
			//sText = gdelement.toString();
			
			break;		
		case updateElement:
			
			Response response = null;
			Command command = new Command(((FirefoxDriver)oWebDriver).getSessionId(),DriverCommand.ELEMENT_EQUALS,ImmutableMap.of("id", ((RemoteWebElement)oWebElement).getId(),"other", ((RemoteWebElement)oWebElement).getId()));
			try {
				response = ((FirefoxDriver)oWebDriver).getCommandExecutor().execute(command);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			elementHelper = new ElementHelper(oWebElement);

			sText = elementHelper.updateGDElement(AutoTool.PageName, ElementHelper.getElementName(), (String)response.getValue());		
			break;
			
		case showElement:
			oJavascriptExecutor.executeScript("return $(arguments[0]).show();", oWebElement);	
			break;
		case hideElement:
			oJavascriptExecutor.executeScript("return $(arguments[0]).hide();", oWebElement);
			break;
		case getElementScreenShot:

			//Get entire page screenshot
			File screenshot = ((TakesScreenshot)oWebDriver).getScreenshotAs(OutputType.FILE);
			BufferedImage fullImg = null;
			try {
				fullImg = ImageIO.read(screenshot);
				//Get the location of element on the page
				Point point = oWebElement.getLocation();
				//Get width and height of the element
				int eleWidth = oWebElement.getSize().getWidth();
				int eleHeight = oWebElement.getSize().getHeight();
				//Crop the entire page screenshot to get only element screenshot
				BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
				ImageIO.write(eleScreenshot, "png", screenshot);
				//Copy the element screenshot to disk
				File screenshotLocation = new File(Paths.get(Property.DefaultPath,"/screenshots/element_screenshot.png").toString());
				FileUtils.copyFile(screenshot, screenshotLocation);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			break;
		
		default:
			return null;	
			
		}
		
		return sText;
		
	}


	

}
