package com.browser.debug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;



public class Property {
	
	
	public static String DefaultPath = loadDefaultPath();;


	public static String fxprofilepath = "";
	private static int Default_Wait_Time = 120;
	private static Properties setting = new Properties();
	
	public static void SetUp()
	{
		
		//get current jar file path
		try {
			DefaultPath = Property.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			DefaultPath = new File(".").getAbsolutePath();
			if(!Property.class.getProtectionDomain().getCodeSource().getLocation().getFile().toString().contains(".jar"))
			{
				DefaultPath = "C:/QA/AutoTool";
			}
			File fxprofile = new File(DefaultPath, "/conf/fxprofile");			
			fxprofilepath = fxprofile.getAbsolutePath();
			System.setProperty("webdriver.firefox.driver", new File(DefaultPath, "/bin/webdriver.xpi").getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", new File(DefaultPath, "/bin/chromedriver.exe").getAbsolutePath());
			System.setProperty("webdriver.ie.driver", new File(DefaultPath, "/bin/IEDriverServer.exe").getAbsolutePath());			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//load properties
		try {
			setting.load(new BufferedReader(new FileReader(new File(DefaultPath, "/conf/setting.conf"))));
			
			AutoTool.CucumberDirectoryPath = setting.getProperty("workspace", "");	

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static int getDefault_Wait_Time() {
		return Default_Wait_Time;
	}

	public static void setDefault_Wait_Time(int default_Wait_Time) {
		Default_Wait_Time = default_Wait_Time;
	}
	
	public static String getDefaultPath() {
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
