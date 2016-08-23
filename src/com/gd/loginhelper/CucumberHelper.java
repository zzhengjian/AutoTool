package com.gd.loginhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.gd.driver.AutoTool;
import com.gd.driver.Customer;



public class CucumberHelper {
	
	public static StringBuilder tcase;
	
	private static HashMap<String, String> custTypeArgs = new HashMap<String, String>();	
	
	public static HashMap<String, String> getCustTypeArgs() {
		return custTypeArgs;
	}


	public static void setCustTypeArgs(HashMap<String, String> custTypeArgs) {
		CucumberHelper.custTypeArgs = custTypeArgs;
	}


	public static void runCommand()
	{
		
		Runtime rt = Runtime.getRuntime();
		Process pr;
		
		int exitVal = 0;
		try {
			pr = rt.exec("cmd /c cucumber auto.feature", null, new File(AutoTool.CucumberDirectoryPath,"Projects\\GreenDot"));
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line=null;
			 
	        while((line=input.readLine()) != null) {
	            System.out.println(line);
	            if(line.contains("UserID:"))
        		{
	            	Customer.UserId = line.split("UserID:")[1].trim();        	
        		}
	            else if(line.contains("Password:"))
	            {
	            	Customer.Password = line.split("Password:")[1].trim();
	            }	            
	            else if(line.contains(Customer.Customer_Not_Exist))
	            {
	            	Customer.UserId = Customer.Customer_Not_Exist;
	            	break;
	            }
	        }
	        
	        exitVal = pr.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}

        
        System.out.println("Exited with error code "+exitVal);
	}
	

	public static void buildCucumberSteps()
	{	
		
		tcase = new StringBuilder();
		tcase.append("Feature: sample").append("\n");
		tcase.append("@sample").append("\n");
		tcase.append("Scenario:  Get customer").append("\n");
		tcase.append("Given I get customerkey use productkey as \"" + custTypeArgs.get("ProductCode") + "\" and customertype as \""+ custTypeArgs.get("CustomerType") +"\" and email like \"\"").append("\n");
		tcase.append("Given I get userid and password from specific customer and store to userid \"UserID\" and password \"Password\"");
	
		
		File file = new File(Paths.get(AutoTool.CucumberDirectoryPath,"Projects\\GreenDot").toString(), "auto.feature");
	
		try {
			FileUtils.writeStringToFile(file, tcase.toString(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getUserId()
	{
		buildCucumberSteps();
		runCommand();
		
	}
	

	
	public static List<String> loadCustomerTypes()
	{
		
		File customertypeFile= new File(AutoTool.CucumberDirectoryPath, "/General/step_definitions/GetSpecificCustomerSteps.rb");
		
		List<String> custList = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(customertypeFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while((line=br.readLine()) != null)
			{
				if(line.startsWith("#customertype"))
				{
					String custType = line.substring(line.indexOf('.') + 1, line.indexOf(':'));
					custList.add(custType);
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

		return custList;
	}
	
}
