package com.gd.loginhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gd.common.Configuration;
import com.gd.driver.Customer;
import com.google.gson.Gson;



public class CucumberHelper {
	private static final Logger logger = LoggerFactory.getLogger(CucumberHelper.class);
	
	
	public static StringBuilder tcase;
	public static Path runPath = Paths.get(Configuration.CucumberWorkspace,"Projects\\GreenDot");
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
			pr = rt.exec("cmd /c cucumber auto.feature", null, new File(runPath.toUri()));
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
			logger.error("Exception: {}", e1);
		}

		logger.info("Exited with error code: {}", exitVal);
	}
	

	private static void buildCucumberSteps()
	{	
		
		tcase = new StringBuilder();
		tcase.append("Feature: sample").append("\n");
		tcase.append("@sample").append("\n");
		tcase.append("Scenario:  Get customer").append("\n");
		tcase.append("Given I get customerkey use productkey as \"" + custTypeArgs.get("ProductCode") + ""
				+ "\" and customertype as \""+ custTypeArgs.get("CustomerType") +""
						+ "\" and email like \"" + custTypeArgs.get("Email") + "\"").append(" with accountbalance >= \"" + custTypeArgs.get("AccountBalance") + ""
								+ "\" and account age less than \""+ custTypeArgs.get("AccountAge") +"\" days").append("\n");
		tcase.append("Given I get userid and password from specific customer and store to userid \"UserID\" and password \"Password\"");
	
		
		File file = new File(runPath.toString(), "auto.feature");
		logger.trace("building case: {}", tcase.toString());
		try {
			FileUtils.writeStringToFile(file, tcase.toString(), "utf-8");
		} catch (IOException e) {
			logger.error("Exception: {}", e);
		}
	}
	
	public static void getUserId()
	{
		buildCucumberSteps();
		runCommand();
		
	}
	

	
	public static List<String> loadCustomerTypes()
	{
		
		File customertypeFile= new File(Configuration.CucumberWorkspace, "/General/step_definitions/GetSpecificCustomerSteps.rb");
		
		List<String> custList = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(customertypeFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			logger.error("File not found Exception: {}", e);
		}
		
		String line = null;
		boolean start = false;
		try {
			while((line=br.readLine()) != null)
			{
				if(line.startsWith("#custTypeDocListStart"))
				{
					start = true;	
					continue;
				}	
				else if(line.startsWith("#custTypeDocListEnd"))
				{
					break;
				}
				
				if(start)
				{
					String custType = line.split("'")[1];
					custList.add(custType);
				}
				
				
			}
			
		} catch (IOException e) {
			logger.error("Exception: {}", e);
		}finally
		{
			
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				logger.error("Exception: {}", e);
			}
		}		

		return custList;
	}
	
	
	public static void getSpecificCustomer(){
		
		Runtime rt = Runtime.getRuntime();
		Process pr;
		
		int exitVal = 0;
		try {
			
			custTypeArgs.put("processor", "TSYS");
			custTypeArgs.put("environment", "QA4");
//			custTypeArgs.put("project", "greendot");
//			custTypeArgs.put("CustomerType", "Perso|NotUserIdCreated");
//			custTypeArgs.put("ProductCode", "GDC30");
			String args = new Gson().toJson(custTypeArgs);
			logger.info("Customer Type Args =: {}", args);
			pr = rt.exec("cmd /c ruby " + Configuration.CucumberWorkspace + "/General/Framework/getspecificcustomer.rb 'non-cucumber' '" + args + "'", null, new File(runPath.toUri()));
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line=null;
			 
	        while((line=input.readLine()) != null) {
	        	logger.info(line);
	            if(line.contains("UserId:"))
        		{
	            	Customer.UserId = line.split("UserId:")[1].trim(); 
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
			logger.error("Exception: {}", e1);
		}

		logger.info("Exited with error code: {}", exitVal);
		
	}
	
}
