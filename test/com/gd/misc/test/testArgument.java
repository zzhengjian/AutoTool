package com.gd.misc.test;

import org.junit.Test;

import com.gd.pages.serializer.PageParser;
import com.gd.steps.serializer.StatementParser;

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
//		String path = "C:\\azheng-QA-Workspace\\QA\\Cucumber\\General\\step_definitions\\page object steps\\basic steps.rb";
//		//String path = "C:\\azheng-QA-Workspace\\QA\\Cucumber\\Projects\\Walmart\\features\\step_definitions\\Web\\Online Activation\\OA_OOW.rb";
//		StatementParser parser = new StatementParser(path);
//		parser.processSteps();		
//		
//		
//		parser.convertCategory();
//		
//		
//	}
	
	@Test
	public void testPageParser()
	{

		
		PageParser parser = new PageParser();
		parser.turnOffConvert();
		PageParser.Skin = "2";
		parser.parse("C:/azheng-QA-Workspace/QA/Cucumber/Projects/Customer Care - CRM/features/pages/web/Case/AddDisputeItemPage.rb");
		
		
	}

	
	
}
