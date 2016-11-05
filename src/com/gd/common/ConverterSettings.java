package com.gd.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConverterSettings {
	
	private static final Logger logger = LoggerFactory.getLogger(ConverterSettings.class);
	
	public static String EndPoint = loadEndPoint();
	
	
	public static String loadEndPoint()
	{
		
		File endPointFile= new File(Property.DefaultPath, "/conf/ConverterConfig.ini");
		
		if(!endPointFile.exists())
		{
			EndPoint = "http://gdcloadperf02:8090/";
			saveEndPoint();
		}		
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(endPointFile);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			logger.debug(EndPoint, e.getStackTrace());
		}
		
		String line = null;
		String workspace = null;
		try {
			while((line=br.readLine()) != null)
			{
				if(line.startsWith("EndPoint"))
				{
					workspace = line.substring(line.indexOf('=') + 1);
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
	
	public static void saveEndPoint()
	{
		File endPointFile= new File(Property.DefaultPath, "/conf/ConverterConfig.ini");
		String line = "EndPoint=" + EndPoint;
    	try {
			FileOutputStream out = new FileOutputStream(endPointFile);
			out.write(line.toString().getBytes()); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
