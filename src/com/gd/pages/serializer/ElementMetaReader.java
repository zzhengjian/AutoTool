package com.gd.pages.serializer;

import java.io.BufferedReader;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class ElementMetaReader {
	private static final Logger logger = LoggerFactory.getLogger(ElementMetaReader.class);
	
	private String line;
	private String currentKey;
	private String currentValue;
	
	private String nestedKey;
	private String nestedValue;
	private JsonObject text;
	private int index;
	
	private boolean parsingText = false;
	
	
	
	public JsonObject metaMatcher(String outline)
	{

		line = getFinalMetaLine(outline);
		logger.trace("meta line: {}",line);
		JsonObject meta = new JsonObject();
		String[] metaline = line.split("=>");
			
		if(metaline.length == 2)
		{
			index = metaline[0].lastIndexOf(':');
			currentKey = metaline[0].substring(index+1).trim();
			currentValue = getMetaValue(metaline[1].trim());
			meta.addProperty(currentKey, currentValue);			
		}
		else{
			
			for(int i = 0; i<metaline.length; i++){		
				
				if(i>0){
					
					if(currentKey.equals("text") && metaline[i].trim().startsWith("{")){
						text = new JsonObject();
						parsingText = true;
						index = metaline[i].lastIndexOf(':');
						nestedKey = metaline[i].substring(index+1).trim();
						continue;						
					}
					
					if(parsingText ){
						nestedValue = metaline[i].trim();
						
						if(nestedValue.endsWith("}")){
							index = metaline[i].lastIndexOf('}');
							nestedValue = getMetaValue(nestedValue.substring(0, index).trim());
							parsingText = false;	
							text.addProperty(nestedKey, nestedValue);
							index = metaline[i].lastIndexOf(':');
							currentKey = metaline[i].substring(index+1).trim();
							meta.add("text", text);
							
							continue;
						}
						else{
							index = metaline[i].lastIndexOf(',');
							nestedValue = getMetaValue(metaline[i].substring(0, index).trim());
						}
						text.addProperty(nestedKey, nestedValue);
						
						//next nested key
						index = metaline[i].lastIndexOf(':');
						nestedKey = metaline[i].substring(index+1).trim();
						
						continue;
					}
					
					if(i != metaline.length - 1){
						index = metaline[i].lastIndexOf(',');
						currentValue = getMetaValue(metaline[i].substring(0, index).trim());
						meta.addProperty(currentKey, currentValue);	
					}
					else{		
						currentValue = metaline[i].trim();
						if(currentValue.endsWith(",")){
							currentValue = currentValue.substring(0, currentValue.lastIndexOf(','));
						}							
						meta.addProperty(currentKey, getMetaValue(currentValue));	
						break;
					}

				}				

				index = metaline[i].lastIndexOf(':');
				currentKey = metaline[i].substring(index+1).trim();			
				
			}				

		}
		
		return meta;		
	}
	
	
	public String getFinalMetaLine(String str) {

	    Stack<Character> stack = new Stack<Character>();
	    stack.push('(');
	    StringBuilder metaLine = new StringBuilder(); 
	    String returnLine = "";

	    char c;
	    for(int i=0; i < str.length(); i++) {
	        c = str.charAt(i);
	        
	        if(c == '\\'){
	        	metaLine.append(c);
	        	i++;
	        	c = str.charAt(i);
	        }	        
	        else if(c == '('){
	        	stack.push('(');
	        }
	        else if(c == ')'){
	        	if(stack.peek() == '(')
	                stack.pop();
	        	if(stack.isEmpty()){
	        		break;
	        	}	        		
	        }	 
	        metaLine.append(c);
	    }
	    
	    returnLine = metaLine.toString().trim();
	    if(returnLine.endsWith(","))
	    	returnLine = returnLine.substring(0, returnLine.lastIndexOf(","));
	    return returnLine;
	}
	
	
	public static String getMetaValue(String str) {

	    Stack<Character> stack = new Stack<Character>();
	    
	    StringBuilder metaValue = new StringBuilder(); 
	    StringBuilder varMeta = new StringBuilder();
	    String returnLine = "";

	    char c;
	    for(int i=0; i < str.length(); i++) {
	        c = str.charAt(i);
	        
	        if(stack.isEmpty()){
        		if(c=='\'' || c=='"'){
        			stack.push(c);
        		}
        		else if(c=='+' ){
        			if(!varMeta.toString().trim().equals(""))
        				metaValue.append("#{").append(varMeta.toString().trim()).append("}");
        		}
        		else if(i==str.length() - 1){
        			varMeta.append(c);
        			metaValue.append("#{").append(varMeta.toString().trim()).append("}");
        		}
        		else if(c == ' '){
        			continue;
        		}
        		else{
        			varMeta.append(c);
        		}
	        	
	        	
	        }
	        else{
	        	varMeta = new StringBuilder();
	        	if(c == '\\'){
		        	//metaValue.append(c);
		        	i++;
		        	c = str.charAt(i);
		        	metaValue.append(c);
		        }	        
		        else if(c == '\''){		        	
		        	if(stack.peek() == c)
		                stack.pop();
		        	
		        	if(stack.isEmpty()){
		        		continue;
		        	}	
		        	else{
		        		metaValue.append(c);
		        	}
		        }
		        else if(c == '"'){
		        	if(stack.peek() == c)
		                stack.pop();
		        	if(stack.isEmpty()){
		        		continue;
		        	}	 
		        	else{
		        		metaValue.append(c);
		        	}
		        }
		        else{
		        	metaValue.append(c);
		        }
	        	
	        }

	    }
	    
	    returnLine = metaValue.toString().trim();
	    return returnLine;
	}


}
