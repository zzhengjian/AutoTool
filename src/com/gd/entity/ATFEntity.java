package com.gd.entity;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gd.common.Utils;


public class ATFEntity {
	
	private static Logger logger = LoggerFactory.getLogger(ATFEntity.class);
	private static ATFEntityManager em = new ATFEntityManager("QA4");
	private static String env;
	public final static String propPath = "C:/QA/ATF/conf";

	
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
	public static Customer getCustomerByAcctNbr(String sAcctNbr)
	{
		
		try
		{
			Query query = em.createQuery("Select * from Customer c where c.serialNbr = :SerialNbr");
			query.setParameter("SerialNbr", Utils.encrypt(sAcctNbr, env));
			return (Customer)query.getSingleResult();
		}
		catch(Exception ex)
		{
			logger.error("Failed to find Customer with Account number:  {}", sAcctNbr);
			logger.error("Exception caught", ex);
		}
		
		return null;
	}

	public static Customer getCustomerBySerialNbr(String sSerialNbr)
	{
		
		try
		{
			Query query = em.createQuery("Select * from Customer c where c.serialNbr = :SerialNbr");
			query.setParameter("SerialNbr", sSerialNbr);
			return (Customer)query.getSingleResult();
		}
		catch(Exception ex)
		{
			logger.error("Failed to find Customer with SeriaNnbr number:  {}", sSerialNbr);
			logger.error("Exception caught", ex);
		}
		
		return null;
	}
	
	public static Customer getCustomerByCustomerKey(String customerKey)
	{		
		try
		{
//			Query query = em.createNativeQuery("Select * from Customer c where c.CustomerKey = 8346208");
//			query.setParameter("customerKey", customerKey);
//			
			return (Customer)em.find(Customer.class, 8346208);
		}
		catch(Exception ex)
		{
			logger.error("Failed to find Customer with SeriaNnbr number:  {}", customerKey);
			logger.error("Exception caught", ex);
		}
		
		return null;
	}
	
	/**
	 * Find a customer by email
	 * 
	 * @param sEmail
	 * (String) Email of the customer
	 * 
	 * @param oEm
	 * (ATFEntityManager) - optional
	 * 
	 * @return
	 * (Customer)
	 */
	public static Customer getCustomerByEmail(String sEmail, Object... oEm)
	{
		ATFEntityManager oManager = oEm.length == 0 ? em : (ATFEntityManager)oEm[0];
		
		try
		{
			String sSQL = "select customerkey from Customer where email = '" + sEmail + "'";
			logger.debug("Executing SQL:  " + sSQL);

			int iCustKey = (Integer)oManager.createNativeQuery(sSQL).getSingleResult();	

			return (Customer)oManager.find(Customer.class, iCustKey);
		}
		catch(Exception ex)
		{
			logger.error("Failed to find available customer", ex);
		}
		
		return null;
		
	}		

}


