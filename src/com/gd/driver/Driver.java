package com.gd.driver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;




public class Driver {

	public static boolean Reload = false;
	public static WebDriver oWebDriver = null;
	
	private HashMap<Enum<Browser>, WebDriver> driverMap = new HashMap<Enum<Browser>, WebDriver>(); 
	
	
	public WebDriver getDriver(String browser)
	{
		return driverMap.get(Enum.valueOf(Browser.class, browser));		
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
	public WebDriver StartWebDriver(String sBrowserType) throws Exception
	{		
		DesiredCapabilities dc;
		
		try
		{
			switch (sBrowserType.toUpperCase())
			{
				case "CHROME":	
					DesiredCapabilities caps = new DesiredCapabilities();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-popup-blocking");
					caps.setCapability(ChromeOptions.CAPABILITY, options);
					oWebDriver = new ChromeDriver(caps);					
					return oWebDriver;
						
				case "IE":
					dc = DesiredCapabilities.internetExplorer();
					dc.setCapability("nativeEvents", true);
					oWebDriver = new InternetExplorerDriver(dc);					
					return oWebDriver;
					
				case "FIREFOX":
					FirefoxProfile profile = new FirefoxProfile(new File(Property.DefaultPath + "/conf/fxprofile"));					
					profile.setPreference("xpinstall.signatures.required", false);
					oWebDriver =  new FirefoxDriver(profile);					
					return oWebDriver;
					
//				case "PHANTOMJS":
//					oWebDriver = new com.browser.debug.phantomjs.PhantomJSDriver();					
//					return oWebDriver;
//					
				default:
					throw new Exception("Failed to instantiate WebDriver!");
					
					
			}			

			
		}
		catch (Exception e)
		{
			throw new Exception("Failed to instantiate WebDriver!", e);
		}
		
		
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
