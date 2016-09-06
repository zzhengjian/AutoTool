package com.gd.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import com.gd.common.Property;
import com.gd.common.Utils;

public class Customer {
	
	public final static String Customer_Not_Exist = "customerkey not exist!"; 
	public static String UserId = "";
	public static String Password = "NECTESTAU0";
	
	public static String FirstName = "Gordon";
	public static String LastName = "James";
	public static String HomePhone = "6267753700";
	public static String CellPhone = "6267753701";
	public static String DOB = "01011975";
	public static String SSN = "";
	public static String Email = getEmail();
	public static String Pin = "3663";
		
	public static HashMap<String, String> ssnMaps = loadRegisterCustomerTypes();	
	public static HashMap<String, String[]> addressMap = loadAddresses();
	public static ArrayList<String> projectList = loadProjects();
	public static HashMap<String, String> projectPath = loadProjectPathMap();	

	private Properties setting = new Properties();		
	
	public Customer()
	{		
		init();
	}
		
	private void init()
	
	{
		try {
			setting.load(new BufferedReader(new FileReader(new File(Property.DefaultPath , "/conf/setting.conf"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	
	}
	
	
	public static String getEmail()
	{
		String email = "";		
		return email = !email.equals("") ? email : "GDAA_" + Utils.getRandomNumeric(18) + "@greendotcorp.com";
		
	}
		
	public static HashMap<String, String[]> loadAddresses()
	{
		File customertypeFile= new File(Property.DefaultPath,"/conf/AddressType");
		
		HashMap<String, String[]> addressMap = new LinkedHashMap<String, String[]>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(customertypeFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while((line=br.readLine()) != null)
			{
				if(!line.equals(""))
				{
					String[] addressType = line.split(",");
					addressMap.put(addressType[0], addressType);
				}				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return addressMap;		
		
	}
	
	
	public static HashMap<String, String> loadRegisterCustomerTypes()
	{
		File customertypeFile= new File(Property.DefaultPath,"/conf/RegisterCustomertype");
		
		HashMap<String, String> custTypeMap = new LinkedHashMap<String, String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(customertypeFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while((line=br.readLine()) != null)
			{
				if(!line.equals(""))
				{
					String[] custType = line.split(":");
					custTypeMap.put(custType[0], custType[1]);
				}				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return custTypeMap;		
		
	}
	
	public static String getGeneratedSSN(String registerType)
	{
		return ssnMaps.get(registerType) + Utils.getRandomNumeric(9 - ssnMaps.get(registerType).length());		
	}
	
	public static ArrayList<String> loadProjects()
	{
		File customertypeFile= new File(Property.DefaultPath,"/conf/Project.ini");
		
		ArrayList<String> projectlist = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(customertypeFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while((line=br.readLine()) != null)
			{
				projectlist.add(line.split(":")[0].trim());		
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return projectlist;		
		
	}
	
	public static HashMap<String, String> loadProjectPathMap()
	{
		File customertypeFile= new File(Property.DefaultPath,"/conf/Project.ini");
		
		HashMap<String, String> projectPathMap = new LinkedHashMap<String, String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(customertypeFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while((line=br.readLine()) != null)
			{
				String[] path = line.split(":");

				projectPathMap.put(path[0], path[1]);	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return projectPathMap;		
		
	}
}
