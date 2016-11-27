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

import com.gd.common.Property;




public class Driver {

	public static boolean Reload = false;
	private static WebDriver oWebDriver = null;
	
	private HashMap<Enum<Browser>, WebDriver> driverMap = new HashMap<Enum<Browser>, WebDriver>(); 
	
	
	public WebDriver getDriver(String browser)
	{
		return driverMap.get(Enum.valueOf(Browser.class, browser));		
	}
	
	
	public static WebDriver getWebDriver()
	{
		if(oWebDriver == null || oWebDriver.toString().contains("null")){
			oWebDriver = StartWebDriver("Firefox");
			if(AutoTool.window!=null && AutoTool.getBtnStart() != null){
				AutoTool.getBtnStart().setText("Stop");
			}
		}
		
		return oWebDriver;
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
