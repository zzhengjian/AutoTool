package com.gd.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import com.gd.pogen.PageObjectType;



public class Property {
	
	
	public static String DefaultPath = loadDefaultPath();
	public static Properties setting = loadSettings();

	public static String url = "";
	public static String parentNodeLocator = "//body";
	public static String SaveToPath = "";
	public static String pageFileName = "";
	public static int PageObject_Type;
	
	public static String fxprofilepath = "";
	private static int Default_Wait_Time = 120;
	
	public static void SetUp()
	{
		
		//get current jar file path
		File fxprofile = new File(DefaultPath, "/conf/fxprofile");			
		fxprofilepath = fxprofile.getAbsolutePath();
		System.setProperty("webdriver.firefox.driver", new File(DefaultPath, "/bin/webdriver.xpi").getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", new File(DefaultPath, "/bin/chromedriver.exe").getAbsolutePath());
		System.setProperty("webdriver.ie.driver", new File(DefaultPath, "/bin/IEDriverServer.exe").getAbsolutePath());			

		
	}

	private static Properties loadSettings() {
		//load properties
		Properties setting = new Properties();
		try {
			setting.load(new BufferedReader(new FileReader(new File(DefaultPath, "/conf/setting.conf"))));			

			parentNodeLocator = setting.getProperty("parentNodeLocator", "");
			PageObject_Type = Integer.parseInt(setting.getProperty("PageObjectType", String.valueOf(PageObjectType.PAGEOBJECT_IN_CUCUMBER)));
			SaveToPath = DefaultPath;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return setting;					
	}

	public static int getDefault_Wait_Time() {
		return Default_Wait_Time;
	}

	public static void setDefault_Wait_Time(int default_Wait_Time) {
		Default_Wait_Time = default_Wait_Time;
	}

	public static void setDefaultPath(String defaultPath) {
		DefaultPath = defaultPath;
	}
	
	public static String loadDefaultPath() {
		String path = "";
		try {
			path = Property.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			path = new File(".").getAbsolutePath();
			if(!Property.class.getProtectionDomain().getCodeSource().getLocation().getFile().toString().contains(".jar"))
			{
				path = "C:/QA/AutoTool";
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
	

}
