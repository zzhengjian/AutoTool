package com.gd.db.test;

import org.junit.Test;

import com.gd.cucumber.entity.Browser;
import com.gd.cucumber.entity.CucumberEntity;
import com.gd.entity.ATFEntity;
import com.gd.entity.Customer;

public class EntityTest {
	

	public void getCustomerTest()
	{
		Customer oCust = ATFEntity.getCustomerByCustomerKey("8346208");	
		System.out.println(oCust.getAccountkey());
		
		
	}
	
	@Test
	public void getBrowserTest()
	{
		Browser browser = CucumberEntity.getBrowser("Firefox");
		System.out.println(browser.getId());
		
		
	}

}
