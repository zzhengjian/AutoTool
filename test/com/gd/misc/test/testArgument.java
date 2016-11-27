package com.gd.misc.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.gd.loginhelper.CucumberHelper;
import com.gd.rest.DefaultResponseHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class testArgument {

	
	
//	@Test
//	public void testArray()
//	{
//		String template = Helper.getDocTemplate("When(/^I verify value in row key \"([^\"]*)\" column \"([^\"]*)\" should (be|contain|match) \"([^\"]*)\" in table \"([^\"]*)\"$/) do |rowKey, column,assertType, text, elementName|", "azheng");
//		
//		System.out.println(template);
//	}
	
//	@Test
//	public void testParser()
//	{
//		String path = "C:\\azheng-QA-Workspace\\QA\\Cucumber\\General\\step_definitions\\GetSpecificCustomerSteps.rb";
//		//String path = "C:\\azheng-QA-Workspace\\QA\\Cucumber\\Projects\\GreenDot\\features\\step_definitions\\Web\\GreenDot\\Flex\\FlexOA\\FlexVerifyUserSteps.rb";
//		StatementParser parser = new StatementParser(path);
//		parser.processSteps();		
//		
//		
//		parser.convertCategory();
//		
//		
//	}
	
//	@Test
//	public void testPageParser()
//	{
//		
//		PageParser parser = new PageParser();
//		parser.turnOffConvert();
//		PageParser.Skin = "7";
//		parser.updatedParse("C:/azheng-QA-Workspace/QA/Cucumber/Projects/Walmart/features/pages/Web/SharedElements.rb");		
//		
//	}

	@Test
	public void testRest(){
		CucumberHelper.getSpecificCustomerFromRest();
	}

	
	
}
