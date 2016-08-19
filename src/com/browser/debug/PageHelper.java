package com.browser.debug;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

public class PageHelper {
	
	public static String getPagePrefix(Projects project)
	{
		String toReturn = null;
		switch(project)
		{
			case GD:
				toReturn =  "GD_";
				break;
			case Nascar:
				toReturn =  "Nascar_";
				break;
			case Rush:
				toReturn =  "Rush_";
				break;
			case Walmart:
				toReturn =  "";
				break;
			case GoBank:
				toReturn = "";
			case FSC:
				toReturn = "";
			default:
				toReturn = "";
				break;
				
		}
		
		return toReturn;
	}
	
	public static Projects getProjectFromUrl(String url)
	{
		if(url.contains("walmartmoneycard"))
			return Projects.Walmart;
		else if(url.contains("racing"))
			return Projects.Nascar;
		else if(url.contains("rush"))
			return Projects.Rush;
		else if(url.contains("greendot") && !url.contains("racing"))
			return Projects.GD;
		else if(url.contains("pos"))
			return Projects.FSC;
		else 
			throw new WebDriverException("Not implemented project");
		
	}

	public static String generatePageNameWithProject(Projects project, String pageName)
	{
		return  getPagePrefix(project) + pageName;
	}
	
	public static StringBuilder buildTopPageLine(StringBuilder page, String url)
	{
		page.append(PageHelper.getPagePrefix(PageHelper.getProjectFromUrl(url)));
		page.append(Utils.getPageNameFromUrl(url));
		page.append(" = GdPage.new(\"");
		page.append(url).append("\")").append("\n\n");
		return page;		
	}
	
	
	public enum Projects {
		
		GD,
		Walmart,
		GoBank,
		FSC,
		Nascar,
		Rush,
	}
	
	public void writeToFile(String pageText)
	{
		try {
			FileUtils.writeStringToFile(new File("C:/test.txt"), pageText, "utf-8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
	}
}
