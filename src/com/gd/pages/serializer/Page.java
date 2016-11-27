package com.gd.pages.serializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Page {
		
	private static final Logger logger = LoggerFactory.getLogger(Page.class);
	
	public final static String SharedElements = "SharedElements";
	private static List<Page> families = new ArrayList<Page>();
	
	private String PageName = "";
	private String Url = "";	
	private String PagePath;	
	private ArrayList<Element> elements = new ArrayList<Element>();
	private ArrayList<String> pageFamilies = new ArrayList<String>();
	
	private Element currentElement;
	private String line = "";
	
	private boolean convertingPage = true; 
	
	public Page(){
		 
	}
	
	public Page(String pageName){
	     this.PageName = pageName;
	}

		
	public static List<Page> getFamilies() {
		return families;
	}
	
	public String getPageName() {
		return PageName;
	}
	
	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public void setElements(ArrayList<Element> elements) {
		this.elements = elements;
	}
	
	public ArrayList<String> getPageFamilies() {
		return pageFamilies;
	}
		
	public void setPageName(String pageName) {
		PageName = pageName;
	}
	
	public String getUrl() {
		return Url;
	}
	
	public void setUrl(String url) {
		Url = url;
	}
	
	public String getPagePath() {
		return PagePath;
	}
	
	public void setPagePath(String pagePath) {
		PagePath = pagePath;
	}
	 
     public void ProcessPage()
     {
    	 logger.info("Parsing Page: {}", PagePath);
    	 BufferedReader br = null;
    	 try {
    		 FileInputStream fis = new FileInputStream(new File(PagePath));
    		 Reader chars = new InputStreamReader(fis, StandardCharsets.UTF_8);
    		 br = new BufferedReader(chars);
    	 } catch (FileNotFoundException e1) {
    		 logger.error("file not found:", e1);
    	 }

         boolean StartPage = false;
         try {
			while ( (line = br.readLine()) != null)
			 {

			     String action = "None";

			     if (line.trim().startsWith("#"))
			     {
			         action = "None";
			     }
			     // Handle processing of GDPage.new declaration
			     else if (line.contains("GdPage.new"))
			     {
			         action = "GdPage";
			         StartPage = true;
			         pageFamilies.add(SharedElements);

			     }
			     // Handle page family assignment
			     else if (line.contains(":pageFamily") || line.contains(".addPageFamily("))
			     {
			         action = "PageFamily";
			     }
			     // Handle element declaration
			     else if (line.contains(".addElement("))
			     {
			         action = "AddElement";
			     }
			     // Handle retrieving Page name from addPage action
			     else if (line.contains("Pages.addPage"))
			     {
			         action = "AddPage";
			     }
			     // Handle retrieving processing addition of a shared element
			     else if (line.contains("Pages.addSharedElement("))
			     {
			         action = "AddSharedElement";
			         StartPage = true;
			     }
			     // Handle adding a page family element
			     else if (line.contains("Pages.addFamilyElement("))
			     {
			         action = "AddFamilyElement";
			         StartPage = true;
			     }
			     // Handle element metadata
			     else if (line.contains("=>"))
			     {
			         action = "AddElementMeta";
			     }
			     
			     if(StartPage) processLine(action,  br);
			 }
			
			
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

     }
     
     
     private void processLine(String actionType, BufferedReader br)
     {
    	 switch (actionType)
         {
             case "Comment":
                 break;
             case "GdPage":
                 String[] pageSplit = line.split("GdPage.new\\(");
                 
                 this.Url = getUrl(pageSplit[1]);
                 
                 if(line.contains(":pageFamily"))
                 {
                	 String[] pageFamily = line.split("pageFamily")[1].split("\"");
                	 this.pageFamilies.add(pageFamily[1].trim());
                 }
                 
                 break;
             case "PageFamily":
                 String[] familySplit = line.split("\"");
                 pageFamilies.add(familySplit[1].trim());
                 break;
             case "AddElement":
                 String[] elementSplit = line.split("\"");
                 currentElement = new Element(elementSplit[1].trim(), line);
                 elements.add(currentElement);

                 break;
             case "AddPage":
                 String[] pageNameSplit = line.split("\"");
                 PageName = pageNameSplit[1];
                 break;
             case "AddSharedElement":
                 String[] sharedElementSplit = line.split("\"");
                 currentElement = new Element(sharedElementSplit[1].trim(), line);
                 Page SharedPage = new Page(SharedElements);

                 if(!families.contains(SharedPage))
                 {
                	 families.add(SharedPage);
                 }
                 for(Page _page : families)
                 {
                	 if(_page.equals(SharedPage))
                	 {
                		 _page.elements.add(currentElement);
                	 }
                 }               
                 
                 break;
             case "AddFamilyElement":
                 String[] familyElementSplit = line.split("\"");
                 Page familyPage = new Page(familyElementSplit[1].trim());
                 currentElement = new Element(familyElementSplit[3].trim(), line);

                 if(!families.contains(familyPage))
                 {
                	 families.add(familyPage);
                 }
                 for(Page page : families)
                 {
                	 if(page.equals(familyPage))
                	 {
                		 page.elements.add(currentElement);
                	 }
                 }    
                 break;
             case "AddElementMeta":
            	 StringBuilder metaline = new StringBuilder();
            	 try {
            		 while(!appendSingleMetaLine(metaline, line)) {
        				line = br.readLine();
        				if(line.trim().startsWith("#")) continue;											
            		 }
            	 } catch (IOException e1) {
            		 logger.error(e1.getMessage(), e1);
            	 }
            	 
            	 JsonObject metas = new ElementMetaReader().metaMatcher(metaline.toString());
            	 
            	 for(Entry<String, JsonElement> meta : metas.entrySet()){
            		 
            		 ElementMeta elementMeta;
            		 if(meta.getKey().equals("text") && meta.getValue().isJsonObject()){
            			 JsonObject texts = metas.getAsJsonObject("text");
            			 for(Entry<String, JsonElement> text : texts.entrySet()){
            				 if(text.getKey().contains("rwd")){
            					 elementMeta = new ElementMeta(text.getKey(), text.getValue().getAsString());
            					 elementMeta.setPlatform("2");
            				 }
            				 else{
            					 elementMeta = new ElementMeta(text.getKey(), text.getValue().getAsString());
            					 elementMeta.setPlatform("1");
            				 }
            				 
            			 }
            				 
            		 }
            		 else{
            			 if(!selectorShouldNotbeEmptyString(meta.getKey(), meta.getValue().getAsString()))
            				 currentElement.addElementMeta(meta.getKey(), meta.getValue().getAsString());
            		 }	 
            		 
            	 }            	 

                 break;
         }

    	 
     }

 	private boolean appendSingleMetaLine(StringBuilder metaLine, String str) {

		Stack<Character> stack = new Stack<Character>();
	    stack.push('(');

	    char c;
	    for(int i=0; i < str.length(); i++) {
	        c = str.charAt(i);
	        
	        if(stack.size() == 1)
	        {
	        	if(c == '#'){
        			break;
        		}  
	        }
	        
	        if(stack.isEmpty()){
    			break;    			        	
	        }
	        else{
	        	if(c == '\\'){
	        		metaLine.append(c);
		        	i++;
		        	c = str.charAt(i);
		        	metaLine.append(c);
		        }	        
		        else if(c == '\''){
		        	
		        	if(stack.peek() == '\''){
		        		stack.pop();
		        	}
		        	else{
		        		stack.push('\'');
		        	}
		        	metaLine.append(c);
	
		        }
		        else if(c == '"'){
		        	
		        	if(stack.peek() == '"'){
		        		stack.pop();
		        	}		               
		        	else{
		        		stack.push('"');
		        	}	   
		        	metaLine.append(c);
		        }
		        else if(c== '('){
		        	stack.push('(');
		        	metaLine.append(c);
		        }
		        else if(c== ')'){
		        	if(stack.peek() == '('){
		        		stack.pop();
		        		if(stack.isEmpty()){
		        			return true;
		        		}
		        	}	
		        	metaLine.append(c);
		        }
		        else{
		        	metaLine.append(c);
		        }
	        	
	        }

	    }
	    
	    return false;
	}
 	
 	private String getUrl(String str) {

	    Stack<Character> stack = new Stack<Character>();
	    
	    StringBuilder url = new StringBuilder(); 
	    StringBuilder varUrl = new StringBuilder();
	    String returnLine = "";

	    char c;
	    for(int i=0; i < str.length(); i++) {
	        c = str.charAt(i);
	        
	        if(stack.isEmpty()){
        		if(c=='\'' || c=='"'){
        			stack.push(c);
        		}
        		else if(c=='+'){
        			if(!varUrl.toString().trim().equals(""))
        				url.append("#{").append(varUrl.toString().trim()).append("}");
        		}
        		else if(c == ',' || c== ')'){
        			if(!varUrl.toString().trim().equals(""))
        				url.append("#{").append(varUrl.toString().trim()).append("}");
        			
        			break;
        		}
        		else if(i==str.length() - 1){
        			varUrl.append(c);
        			url.append("#{").append(varUrl.toString().trim()).append("}");
        		}
        		else if(c == ' '){
        			continue;
        		}  
        		
        		else{
        			varUrl.append(c);
        		}       	
	        	
	        }
	        else{
	        	varUrl = new StringBuilder();
	        	if(c == '\\'){
		        	url.append(c);
		        	i++;
		        	c = str.charAt(i);
		        }	        
		        else if(c == '\''){
		        	
		        	if(stack.peek() == c)
		                stack.pop();
		        	if(stack.isEmpty()){
		        		continue;
		        	}	
		        }
		        else if(c == '"'){
		        	
		        	if(stack.peek() == c)
		                stack.pop();
		        	if(stack.isEmpty()){
		        		continue;
		        	}	        		
		        }
		        else{
		        	url.append(c);
		        }
	        	
	        }

	    }
	    
	    returnLine = url.toString().trim();
	    return returnLine;
	}
     
     private boolean selectorShouldNotbeEmptyString(String metaKey, String metaValue){
    	 return ("".equals(metaValue) && (metaKey.contains("css") || metaKey.contains("xpath")));
    	 
     }
     
     public void addElement(Element e)
     {
    	
    	int index = 0;
 		while(!convertingPage && elements.contains(e))
 		{
 			//make duplicated elementName to be ElementName_Tag_1
 			String elementName = e.getElementName();
 			int len = elementName.split("_").length;

 			try { 				
 				index = Integer.parseInt(elementName.split("_")[len-1]);				
 				e.setElementName(elementName.replace("_"+index, "_" + (index + 1)));				
 			} catch (NumberFormatException e1) {
 				
 				e.setElementName(elementName + "_" + (index + 1));
 			}			
 		}
 		
 		elements.add(e);    		 	 
     }
     
     
 	public void removeElement(Element e)
 	{
 		elements.remove(e);	
 	}
 	
 	
 	public int getElementCount()
 	{		
 		return elements.size();
 	}
     
     public void savePage()
     {
    	 
    	 
     }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PageName == null) ? 0 : PageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (PageName == null) {
			if (other.PageName != null)
				return false;
		} else if (!PageName.equals(other.PageName))
			return false;
		return true;
	}
     
     
}
