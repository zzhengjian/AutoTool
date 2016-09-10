package com.gd.db.test;

import org.junit.Test;

import com.gd.entity.ATFEntity;
import com.gd.entity.Customer;

public class EntityTest {
	
	@Test
	public void getCustomerTest()
	{
		Customer oCust = ATFEntity.getCustomerByCustomerKey("8346208");	
		System.out.println(oCust.getAccountkey());
		
		
	}

}
