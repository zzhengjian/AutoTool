package com.gd.driver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gd.common.Property;




public class Driver {

	private static WebDriver oWebDriver;
	
	private static String parentNode = "//body";
	private static String currentUrl = "";

	private HashMap<Enum<Browser>, WebDriver> driverMap = new HashMap<Enum<Browser>, WebDriver>(); 
	
	
	public WebDriver getDriver(String browser)
	{
		return driverMap.get(Enum.valueOf(Browser.class, browser));		
	}
	
	
	public static WebDriver getWebDriver()
	{
		if(oWebDriver == null || oWebDriver.toString().contains("null")){
			oWebDriver = StartWebDriver("Chrome");

		}
		
		return oWebDriver;
	}
	
	
	public static String getParentNode() {
		return parentNode;
	}


	public static void setParentNode(String parentNode) {
		Driver.parentNode = parentNode;
	}
	
	
	public static String getCurrentUrl() {
		return currentUrl;
	}


	public static void setCurrentUrl(String currentUrl) {
		Driver.currentUrl = currentUrl;
	}
	
	/**
	 * Instantiate browser specific WebDriver based on browser type.
	 * 
	 * @param sBrowserType
	 * (String)
	 * 
	 * @return
	 * (WebDriver)
	 * 
	 * @throws Exception
	 */
	public static WebDriver StartWebDriver(String sBrowserType)
	{		

		switch (sBrowserType.toUpperCase())
		{
			case "CHROME":	
				oWebDriver = startChrome();					
				break;
					
			case "IE":
				oWebDriver = startIE();				
				break;
				
			case "FIREFOX":
				oWebDriver =  startFirefox();					
				break;
				
			default:
				oWebDriver =  startFirefox();			
				break;				
				
		}			
		return oWebDriver;
		
	}
	
	private static WebDriver startFirefox(){
		FirefoxProfile profile = new FirefoxProfile(new File(Property.DefaultPath + "/conf/fxprofile"));					
		profile.setPreference("xpinstall.signatures.required", false);
		return  new FirefoxDriver(profile);	
	}
	
	private static WebDriver startChrome(){
		DesiredCapabilities caps = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-popup-blocking");
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(caps);
	}
	
	private static WebDriver startIE(){		
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		dc.setCapability("nativeEvents", true);
		return new InternetExplorerDriver(dc);
	}
	
	public static void quitDriver(){
		if(oWebDriver != null)
			oWebDriver.quit();
	}
	
	
	public void setPageLoadTimeOut(int seconds){
		if(oWebDriver != null)
			oWebDriver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);		
	}
	
	public void setImplicitylyWait(int seconds){
		if(oWebDriver != null)
			oWebDriver.manage().timeouts().implicitlyWait(seconds * 1000, TimeUnit.MILLISECONDS);		
	}
	
	/**
	 * Instantiate RemoteWebDriver based on browser type.  This is used for Grid.
	 * 
	 * @param sBrowserType
	 * (String)
	 * 
	 * @return
	 * (WebDriver)
	 * 
	 * @throws Exception
	 */
	public WebDriver StartRemoteWebDriver(String sBrowser, URL remoteAddress) throws Exception
	{

		DesiredCapabilities dc = null;
		RemoteWebDriver oRemoteWebDriver;
		String[] aBrowserInfo = sBrowser.split("\\|");   // Browser info consist of BrowserType|Version
		boolean bMaximizeBrowser = true;
		
		switch (aBrowserInfo[0].toUpperCase())
		{
			case "CHROME":
				dc = DesiredCapabilities.chrome();
				if (bMaximizeBrowser)
				{
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--start-maximized");
					dc.setCapability(ChromeOptions.CAPABILITY, options);
					//dc.setCapability("chrome.switches", java.util.Arrays.asList("--start-maximized"));
				}
				break;	
			case "IE":
				dc = DesiredCapabilities.internetExplorer();
				dc.setCapability("nativeEvents", true);
				dc.setCapability("platform", "ANY");
				//dc.setCapability("ensureCleanSession", false);
				break;
			case "FIREFOX":
				FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(false);
				dc = DesiredCapabilities.firefox();
				//dc.setCapability(org.openqa.selenium.firefox.FirefoxDriver.PROFILE, profile);
				break;
			default:
				throw new Exception("Failed to instantiate RemoteWebDriver!");
				
		}
		
		try
		{
			if (aBrowserInfo.length > 1)
				dc.setVersion("11");
				//dc.setCapability("version", aBrowserInfo[1]);
			
			
			oRemoteWebDriver =  new RemoteWebDriver(remoteAddress, dc);
			return oRemoteWebDriver;
		}
		catch (Exception e)
		{
			//logger.error("Failed to instantiate RemoteWebDriver:  {}", e);
			throw new Exception("Failed to instantiate RemoteWebDriver!", e.getCause());
		}
	}

}
