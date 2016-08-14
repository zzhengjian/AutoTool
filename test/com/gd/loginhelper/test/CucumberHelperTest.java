package com.gd.loginhelper.test;

import java.util.List;
import com.gd.loginhelper.CucumberHelper;
import org.junit.Test;

public class CucumberHelperTest {
	
	
	@Test
	public void printCustomerTypes()
	{
		List<String> custList = CucumberHelper.loadCustomerTypes();
		for(String type : custList)
		{
			System.out.println(type);
			
		}
		System.out.println(custList.size());
		
	}

}
