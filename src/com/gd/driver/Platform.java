package com.gd.driver;

public class Platform {
	
	public final static String Desktop = "desktop";
	public final static String RWD = "rwd";
	public final static String Mobile = "mobile";
	
	private static String CurrentPlatform = Desktop;
	
	public static String getPlatform(){
		 return CurrentPlatform;
	}
	
	public static void setPlatform(String platform){
		 CurrentPlatform = platform;
	}
	

}
