package com.gd.steps.doc;

public class Helper {
	
	
	public static String getDocTemplate(String line, String author)
	{
		
		int doIndex = line.indexOf("do" , line.lastIndexOf("/")); 
		int start = line.indexOf("|", doIndex); 
		int end = line.lastIndexOf("|");
		String[] params = null;
		String temp = "";
		
		
		StringBuilder template = new StringBuilder();
				
		template.append("#Start_DOC").append("\n");
		template.append("#Author:: ").append(author).append("\n");
		template.append("#Desc:: ").append("\n");
		template.append("#params:: ").append("\n");
		
		if(start > -1)
		{
			//System.out.println(line);
			params = line.substring(start+1, end).split(",");
			int argindex = 0;
			for(String param : params)
			{
				if(!"".equals(param))
				{			
					temp = "#" + param.trim() + "::";
					template.append(temp).append("\n");
					argindex++;
				}
				
			}
		}
		template.append("#Returns:: ").append("\n");
		template.append("#Example:: ").append("\n");
		template.append("#End_DOC:: ").append("\n");		
		template.append(line);
		
		return template.toString();
	}

	
	
}
