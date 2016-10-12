package com.gd.pages.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.google.gson.Gson;


public class PageParser {
	
	public static String Skin = "1";
	
	@Test
	public void testParser()
	{
		parse("C:/azheng-QA-Workspace/QA/Cucumber/Projects/GreenDot/features/pages/Web/GreenDot/SharedElements.rb");
	}

	public void parse(String path)
	{
		String json = null;
		Page page = new Page();
		page.setPagePath(path);
		//page.setPageName(Page.SharedElements);
		if(path.contains(Page.SharedElements))
		{
			page.setPageName(Page.SharedElements);
			page.ProcessPage();
			List<Page> families = page.getFamilies();
			for(Page family : families)
			{
				json = new Gson().toJson(serializePage(family, FamilySerializer.class));
				try {
					whenPostStringRequestUsingHttpClient(json, "http://127.0.0.1:8000/pm-cw/savefamily/");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			page.ProcessPage();
			json = new Gson().toJson(serializePage(page, PageSerializer.class));
			try {
				whenPostStringRequestUsingHttpClient(json, "http://127.0.0.1:8000/pm-cw/savepage/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(json);

	}
	
	public void whenPostStringRequestUsingHttpClient(String json, String endPoint) 
			  throws ClientProtocolException, IOException {
			    CloseableHttpClient client = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost(endPoint);
			 			    
			    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
				params.add(new BasicNameValuePair("myjsondata", json));
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			    CloseableHttpResponse response = client.execute(httpPost);
			    //System.out.println(response.getStatusLine().getStatusCode());
			    client.close();
			}
	
	public void whenPostJsonRequestUsingHttpClient(String json) 
			  throws ClientProtocolException, IOException {
			    CloseableHttpClient client = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost("http://127.0.0.1:8000/pm-cw/savepage/");
			 
			    StringEntity entity = new StringEntity(json);
			    httpPost.setEntity(entity);
			    httpPost.setHeader("Accept", "application/json");
			    httpPost.setHeader("Content-type", "application/json");
			    			 
			    CloseableHttpResponse response = client.execute(httpPost);
			    System.out.println(response.getStatusLine().getStatusCode());
			    client.close();
			}
	
	
	public <T> T serializePage(Page page, Class<T> pageType)
	{
		T serializer;		
		if(pageType.equals(FamilySerializer.class))
		{
			FamilySerializer familyserializer = null;
			familyserializer = new FamilySerializer();

			familyserializer.familyName = page.getPageName() == null ? "" : page.getPageName();
			familyserializer.URL = "";
			familyserializer.skin = Skin;
		    
		    for(Element e : page.getElements())
		    {
		    	ElementSerializer eserializer = new ElementSerializer();
                eserializer.elementName = e.getElementName().replace("\"", "").trim();
                eserializer.frame = "";
		    	for(ElementMeta meta : e.getMetas())
		    	{
		    		ElementMetaSerializer metaserializer = new ElementMetaSerializer();
		    		if (meta.getKey().contains("rwd"))
                    {
                        metaserializer.metaKey = "selector";
                        metaserializer.metaValue = meta.getValue();
                        metaserializer.browser = "";
                        metaserializer.platform = "2";
                        metaserializer.description = "xxx";


                    }
                    else if (meta.getKey().contains("desktop"))
                    {
                        metaserializer.metaKey = "selector";
                        metaserializer.metaValue = meta.getValue();
                        metaserializer.browser = "";
                        metaserializer.platform = "1";
                        metaserializer.description = "xxx";
                    }
                    else
                    {
                        metaserializer.metaKey = meta.getKey().replace(":", "");
                        metaserializer.metaValue = meta.getValue();
                        metaserializer.browser = "";
                        metaserializer.platform = "";
                        metaserializer.description = "xxx";
                    }
		    		metaserializer.screenShot = "";
                    eserializer.elementMetas.add(metaserializer);
                    eserializer.description = "";
		    	}
		    	familyserializer.elements.add(eserializer);			    	
		    }

			serializer = (T) familyserializer;
		}
		else
		{
			PageSerializer pserializer = new PageSerializer();
			pserializer.pageName = page.getPageName() == null ? "" : page.getPageName();
			pserializer.URL = page.getUrl();
			pserializer.skin = Skin;
		    pserializer.screenShot = "";
		    for(String pageFamily : page.getPageFamilies())
		    {
		    	pserializer.pageFamilies.add(pageFamily);
		    }
		    
		    for(Element e : page.getElements())
		    {
		    	ElementSerializer eserializer = new ElementSerializer();
                eserializer.elementName = e.getElementName().replace("\"", "").trim();
                eserializer.page = page.getPageName() == null ? "" : page.getPageName();
                eserializer.family = "";
                eserializer.frame = "";
		    	for(ElementMeta meta : e.getMetas())
		    	{
		    		ElementMetaSerializer metaserializer = new ElementMetaSerializer();
		    		if (meta.getKey().contains("rwd"))
                    {
                        metaserializer.metaKey = "selector";
                        metaserializer.metaValue = meta.getValue();
                        metaserializer.browser = "";
                        metaserializer.platform = "2";
                        metaserializer.description = "";


                    }
                    else if (meta.getKey().contains("desktop"))
                    {
                        metaserializer.metaKey = "selector";
                        metaserializer.metaValue = meta.getValue();
                        metaserializer.browser = "";
                        metaserializer.platform = "1";
                        metaserializer.description = "xxx";
                    }
                    else if (meta.getKey().contains("parentFrame"))
                    {
                        metaserializer.metaKey = "frame";
                        metaserializer.metaValue = meta.getValue();
                        metaserializer.browser = "";
                        metaserializer.platform = "";
                        metaserializer.description = "";
                    }
                    else
                    {
                        metaserializer.metaKey = meta.getKey().replace(":", "");
                        metaserializer.metaValue = meta.getValue();
                        metaserializer.browser = "";
                        metaserializer.platform = "";
                        metaserializer.description = "";
                    }
                    metaserializer.element = e.getElementName().replace("\"", "").trim();
                    eserializer.elementMetas.add(metaserializer);
                    eserializer.description = "";
		    	}
		    	pserializer.elements.add(eserializer);
		    }
		    serializer = (T) pserializer;
		}
		
		return serializer;
	}
	
}
