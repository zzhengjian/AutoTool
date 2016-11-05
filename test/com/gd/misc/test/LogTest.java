package com.gd.misc.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LogTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

	@Test
	public void testLog()
	{
		
		FileReader fr = null;
		BufferedReader br = null;
		String endPointFile = "C:/filepath";
		try {
			
			fr = new FileReader(endPointFile );
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		logger.error("an error");
		logger.debug("debug");
		logger.info("this is a info");
		logger.trace("a log trace");
	}	

}
