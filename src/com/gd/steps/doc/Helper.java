package com.gd.steps.doc;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.gd.common.ConverterSettings;
import com.gd.rest.DefaultResponseHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

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
		if(start > -1)
		{
			//System.out.println(line);
			template.append("#params:: ").append("\n");
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

	public static JsonElement getProjects()
	{
		String url = null;
		try {
			url = new URIBuilder(ConverterSettings.EndPoint).setPath("/writer/api-cw/allprojects").toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonElement projects = getJsonResponse(url);

		return projects;
		
	}
	
	public static JsonElement getSkinsByProject(String project)
	{
		String url = null;
		try {
			url = new URIBuilder(ConverterSettings.EndPoint).setPath("/writer/pm-cw/get-skins").addParameter("project", project).toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonElement skins = getJsonResponse(url);

		return skins;
		
	}
	
	private static JsonElement getJsonResponse(String url)
	{
		JsonElement response = null;
		try {
			response = getRequestUsingHttpClient(url);
		}  catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;	
		
	}
	
	private static JsonElement getRequestUsingHttpClient(String endPoint) 
			  throws ClientProtocolException, IOException {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpget = new HttpGet(endPoint);
				 
				System.out.println("Triggering request " + httpget.getRequestLine());
				 
				// Custom Response Handler
				ResponseHandler<String> responseHandler = new DefaultResponseHandler();
				String responseBody = httpclient.execute(httpget, responseHandler);
				
				
			    return new Gson().fromJson(responseBody, JsonArray.class);
			}

	
}
