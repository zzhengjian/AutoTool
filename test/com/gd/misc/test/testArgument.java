package com.gd.misc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gd.steps.doc.Helper;
import com.gd.steps.serializer.Argument;
import com.gd.steps.serializer.StatementParser;
import com.google.gson.Gson;

public class testArgument {

	
	
//	@Test
//	public void testArray()
//	{
//		String template = Helper.getDocTemplate("When(/^I verify value in row key \"([^\"]*)\" column \"([^\"]*)\" should (be|contain|match) \"([^\"]*)\" in table \"([^\"]*)\"$/) do |rowKey, column,assertType, text, elementName|", "azheng");
//		
//		System.out.println(template);
//	}
	
	@Test
	public void testParser()
	{
		//String path = "C:\\azheng-QA-Workspace\\QA\\Cucumber\\General\\step_definitions\\rest steps\\rest steps.rb";
		String path = "C:\\azheng-QA-Workspace\\QA\\Cucumber\\General\\step_definitions\\Table Steps.rb";
		StatementParser parser = new StatementParser(path);
		parser.processSteps();
		
		
		
		//parser.convertCategory();
		
		
	}
}
