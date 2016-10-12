package com.gd.cucumber.entity;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gd.common.Utils;
import com.gd.entity.ATFEntityManager;


public class CucumberEntity {
	
	private static Logger logger = LoggerFactory.getLogger(CucumberEntity.class);
	private static ATFEntityManager em = new ATFEntityManager("Cucumber");
	private static String env;

	
	////////////////////////////////////////
	//  Customer/Account related methods  //
	//////////////////////////////////////
	
	/**
	 * Find a customer by account number.
	 * 
	 * @param sAcctNbr
	 * (String) - Decrypted account number.
	 * 
	 * @param oEm
	 * (ATFEntityManager) - optional.
	 * 
	 * @return
	 * (Customer)
	 */
	public static Browser getBrowser(String browser)
	{
		
		try
		{
			Query query = em.createQuery("Select * from Browser b where b.browserName = :browser");
			return (Browser)query.getSingleResult();
		}
		catch(Exception ex)
		{
			logger.error("Failed to find Customer with Account number:  {}", browser);
			logger.error("Exception caught", ex);
		}
		
		return null;
	}


}


