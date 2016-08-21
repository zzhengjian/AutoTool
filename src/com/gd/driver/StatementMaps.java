package com.gd.driver;

import java.util.HashMap;


public class StatementMaps {
	
	
	String statement = 	null;
	
	public static String[] sNavigation = {"visit","on"};
	public static String[] sElementOperation = {"click","clickIfExist","fill", "fillIfExist","select","mouseover","check","uncheck","clearvalue","doubleclick","storetext","storevalue"};
	public static String[] sElementVerification = {"ElementText","ElementValue","backgroundcolor","color","isdisabled","isenabled","ischecked","isnotchecked","isviewable","isnotviewable","isfocused","Attribute", "CssValue"};	
	public static String[] sPageVerification = {"doescontain","doesContainText","doesnotcontain","doesNotContainText"};
	public static String[] sBrowserOperation = {"open_new_window", "movetowindow", "maximize_window", "close_and_movebacktowindow","reset_browser","reload_page","goback","goforward","accept_alert","dismiss_alert",};
	
	public String getStatement(String ...sCommand)
	{
		
		//navigation
		if(sCommand[0].equalsIgnoreCase("visit"))
			statement = "Given I visit \"" + sCommand[1] + "\" page";
		
		else if(sCommand[0].equalsIgnoreCase("on"))
			statement = "When I am on the page \"" + sCommand[1] + "\"";
		
		//element verification
		else if(sCommand[0].matches("(isdisabled|isenabled|isnotchecked|ischecked|isviewable|isnotviewable)"))
			statement = "Then \"" +  sCommand[1] + "\" field " + sCommand[0];
		
		else if(sCommand[0].matches("(click|clickIfExist|mouseover|check|uncheck)")&&sCommand.length==2)
			statement = "Then I " + sCommand[0] + " \"" + sCommand[1] + "\"";		
		
		else if(sCommand[0].matches("(fill|fillIfExist|select)")&&sCommand.length==3)
			statement = "Then I " + sCommand[0] + " \""  + sCommand[1] + "\"" + " with " +  "\"" + sCommand[2] + "\"";
		
		else if(sCommand[0].matches("(Attribute|CssValue)")&&sCommand.length==3)
			statement = "Then The " + sCommand[0] + " of element " + "\"" + sCommand[1] + "\"" + " is" + "\"" +  sCommand[2] + "\"";
		
		else if(sCommand[0].matches("(backgroundcolor|color|border|alignment)"))
		{
			if(sCommand[0].matches("(alignment)"))
			{
				return statement = "Then The " + sCommand[0] + " of element " + "\"" + sCommand[1] + "\"" + " is" + "\"" +  sCommand[2] + "\"";
			}					
			statement = "Then The " + sCommand[0] + " of element " + "\"" + sCommand[1] + "\"" + " is" + "\"" +  sCommand[2] + "\" in hexCode";	
		}

		//page verification
		else if(sCommand[0].matches("(doescontain|doesContainText|doesnotcontain|doesNotContainText)")&&sCommand.length==2)
			statement = "Then Page " + sCommand[0] + " \""  + sCommand[1] + "\"";		
		
		
		
		else
			statement = null;
		
		
		return statement;
		
	}	
	
	public String getStatement(HashMap<String,String> command)
	{
		//navigation
		if(command.get("Action").equalsIgnoreCase("visit"))
			statement = "Given I visit \"" + command.get("PageName") + "\" page";
		
		else if(command.get("Action").equalsIgnoreCase("on"))
			statement = "When I am on the page \"" + command.get("PageName") + "\"";
		
		//element verification
		else if(command.get("Action").matches("(isdisabled|isenabled|isnotchecked|ischecked|isviewable|isnotviewable)"))
			statement = "Then \"" +  command.get("ElementName") + "\" field " + command.get("Action");
		
		else if(command.get("Action").matches("(click|clickIfExist|mouseover|check|uncheck|doubleclick|clearvalue)"))
			statement = "Then I " + command.get("Action") + " \"" + command.get("ElementName") + "\"";		
		
		else if(command.get("Action").matches("(fill|fillIfExist|select)"))
			statement = "Then I " + command.get("Action") + " \""  + command.get("ElementName") + "\"" + " with " +  "\"" + command.get("ExpectedText") + "\"";
		
		else if(command.get("Action").matches("(Attribute|CssValue)"))
			statement = "Then verify the " + command.get("Action") + " \"" + command.get("Attribute_CssValue") + "\" of element " + "\"" + command.get("ElementName") + "\" " + command.get("AssertType") + " value \"" +  command.get("ExpectedText") + "\"";

		else if(command.get("Action").matches("(ElementText)"))
			statement = "Then verify the text of element " + "\"" + command.get("ElementName") + "\" " + command.get("AssertType") + " \"" +  command.get("ExpectedText") + "\"";
		
		else if(command.get("Action").matches("(ElementValue)"))
			statement = "Then verify the value of element " + "\"" + command.get("ElementName") + "\" " + command.get("AssertType") + " value \"" +  command.get("ExpectedText") + "\"";

			
		
		else if(command.get("Action").matches("(backgroundcolor|color|border|alignment)"))
		{
			if(command.get("Action").matches("(alignment)"))
			{
				return statement = "Then The " + command.get("Action") + " of element " + "\"" + command.get("ElementName") + "\"" + " is" + "\"" +  command.get("ExpectedText") + "\"";
			}					
			statement = "Then The " + command.get("Action") + " of element " + "\"" + command.get("ElementName") + "\"" + " is" + "\"" +  command.get("ExpectedText") + "\" in hexCode";	
		}

		//page verification
		else if(command.get("Action").matches("(doescontain|doesContainText|doesnotcontain|doesNotContainText)"))
		{
			if(command.get("Action").matches("(doesContainText|doesNotContainText)"))
			{
				statement = "Then Page " + command.get("Action") + " \""  + command.get("ExpectedText") + "\"";	
			}
			else
			{
				statement = "Then Page " + command.get("Action") + " \""  + command.get("ElementName") + "\"";	
			}
			
		}	
		else if(command.get("Action").matches("(movetowindow)"))
			statement = "Then I movetoWindow " + "\""  + command.get("ExpectedText") + "\"";
		else if(command.get("Action").matches("(close_and_movebacktowindow)"))
			statement = "Then I close and moveBacktoWindow " + "\""  + command.get("ExpectedText") + "\"";
		
		else if(stepsMap().containsKey(command.get("Action")))			
			statement = "Then " + stepsMap().get(command.get("Action"));
		else 
			statement = null;		
		
		
		return statement;
	
	}
	
	
	//{"open_new_window", "movetowindow", "maximize_window", "close_and_movebacktowindow","reset_browser","reload_page","goback","goforward","accept_alert","dismiss_alert",};
	private static HashMap<String,String> stepsMap()
	{
		HashMap<String,String> stepsMap = new HashMap<String,String>();
		
		stepsMap.put("open_new_window", "I open new window");
		stepsMap.put("movetowindow", "");
		stepsMap.put("maximize_window", "I maximize the window");
		stepsMap.put("close_and_movebacktowindow", "");
		stepsMap.put("reset_browser", "I reset browser");
		stepsMap.put("reload_page", "I reload page");
		stepsMap.put("goback", "I navigate back");
		stepsMap.put("goforward", "I navigate forward");
		stepsMap.put("accept_alert", "I accept alert popup");
		stepsMap.put("dismiss_alert", "I dismiss alert popup");
		
		return stepsMap;
	}


}
