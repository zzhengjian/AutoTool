package com.gd.pogen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gd.driver.Driver;
import com.gd.driver.ElementHelper;
import com.gd.driver.Property;
import com.gd.driver.Utils;
import com.gd.pagetree.ElementBean;
import com.gd.pagetree.PageBean;
import com.google.common.collect.ImmutableMap;

public class ElementGenerator {
	
	private String url;
	
	public void GeneratePageObject(WebDriver oWebDriver) {
		// TODO Auto-generated method stub
		url = Property.url;
		WebDriverWait wait = new WebDriverWait(oWebDriver, 5000);
		wait.until(PageHelper.pageLoaded(oWebDriver));
		List<WebElement> allElements = new ArrayList<WebElement>();
		wait.until(ExpectedConditions.presenceOfElementLocated(Utils.getBy(Property.parentNodeLocator)));
		for(String xpath : ElementHelper.xpaths())
		{
			WebElement parent = oWebDriver.findElement(Utils.getBy(Property.parentNodeLocator));
			allElements.addAll(parent.findElements(By.xpath(xpath)));
		}

		String pageName = Property.pageFileName.equals("") ? PageHelper.generatePageNameWithUrl(url) : Property.pageFileName;
		String fileName = pageName.contains("_") ? pageName.split("_")[1] : pageName;
		PageBean page = new PageBean(url,pageName);
		for(WebElement e : allElements)
		{
			
			if(e.isDisplayed())
			{				
				String selector = getSelectFromElement(e);				
				
				if("".equals(selector))
				{
					continue;
				}				
				
				ElementHelper elementHelper = new ElementHelper(e);
				String elementName = elementHelper.evaluateElementName();
				
				//store elements to a data model
				ElementBean ele = new ElementBean(elementName, selector);
				if(!"".equals(elementHelper.getText()) && !e.getTagName().toLowerCase().matches("select"))
				{
					ele.addDefaultValue("text", elementHelper.getText());
				}
				page.addElement(ele);
			}			
				
		}			

		//store page to a file
		try {
			String SavePath = Property.SaveToPath.equals("") ? Property.DefaultPath : Property.SaveToPath;
			if(SavePath.equals(Property.DefaultPath))
			{
				File dir = new File(SavePath, "/pages/");
				if(dir.exists() && dir.isDirectory())
				{
					SavePath = dir.getAbsolutePath();
				}
				
				if(!dir.exists() || !dir.isDirectory())
				{
					dir.mkdir();
					SavePath = dir.getAbsolutePath();
				}					
			}
			
			Save save = new Save(page);

			File pagefile = save.toPageObjectFile(SavePath,fileName, Property.PageObject_Type);	
			//delete file if already exists
			//File pagefile = new File(SavePath, "" + pageName + ".rb");
			if(pagefile.exists())
			{
				pagefile.delete();				
			}
		
			FileUtils.writeStringToFile(pagefile, save.getPageStream().toString(), "utf-8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String getSelectFromElement(WebElement e) {
		
		WebDriver driver = Driver.oWebDriver;
		Response response = null;
		String selector = "";
		Command command = new Command(((RemoteWebDriver)driver).getSessionId(),DriverCommand.ELEMENT_EQUALS,ImmutableMap.of("id", ((RemoteWebElement)e).getId(),"other", ((RemoteWebElement)e).getId()));
		try {
			response = ((RemoteWebDriver)driver).getCommandExecutor().execute(command);
			selector = (String)response.getValue();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassCastException e2)
		{
			e2.printStackTrace();
		}	
		
		return selector;
	}

	
	

}
