package com.gd.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Configuration {
	
	public static String CucumberWorkspace = loadWorkspace(); 

	
	public static String loadWorkspace()
	{
		
		File workspaceFile= new File(Property.DefaultPath, "/conf/CucumberWorkspace.ini");
		
		if(!workspaceFile.exists())
		{
			CucumberWorkspace = "C:\\azheng-QA-Workspace\\QA\\Cucumber";
			saveWorkspace();
		}
					
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(workspaceFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line = null;
		String workspace = null;
		try {
			while((line=br.readLine()) != null)
			{
				if(line.startsWith("RECENT_WORKSPACES"))
				{
					workspace = new File(line.substring(line.indexOf('=') + 1)).toString();
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

		return workspace;
	}
	
	public static void saveWorkspace()
	{
		File workspaceFile= new File(Property.DefaultPath, "/conf/CucumberWorkspace.ini");
		String line = "RECENT_WORKSPACES=" + CucumberWorkspace;
    	try {
			FileOutputStream out = new FileOutputStream(workspaceFile);
			out.write(line.toString().getBytes()); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
