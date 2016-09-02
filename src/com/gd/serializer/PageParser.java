package com.gd.serializer;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
		String json;
		Page page = new Page();
		page.setPagePath(path);
		//page.setPageName(Page.SharedElements);
		if(path.contains(Page.SharedElements))
		{
			page.setPageName(Page.SharedElements);
			page.ProcessPage();
			json = new Gson().toJson(serializePage(page, SharedElementSerializer.class));
		}
		else
		{
			page.ProcessPage();
			json = new Gson().toJson(serializePage(page, PageSerializer.class));
		}

		System.out.println(json);
		try {
			whenPostRequestUsingHttpClient_thenCorrect(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void whenPostRequestUsingHttpClient_thenCorrect(String json) 
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
		PageSerializer pserializer = null;
		SharedElementSerializer shserializer = new SharedElementSerializer();
		if(page.getPageName().equals(Page.SharedElements))
		{
			for(Page _page : page.getFamilies())
			{
				pserializer = new PageSerializer();
				pserializer.pageName = "";
				pserializer.familyName = _page.getPageName() == null ? "" : _page.getPageName();
				pserializer.URL = "";
				pserializer.skin = Skin;
			    pserializer.screenShot = "";
			    pserializer.createBy = "azheng";
			    for(String pageFamily : _page.getPageFamilies())
			    {
			    	pserializer.families.add(pageFamily);
			    }
			    
			    for(Element e : _page.getElements())
			    {
			    	ElementSerializer eserializer = new ElementSerializer();
	                eserializer.elementName = e.getElementName().replace("\"", "").trim();
	                eserializer.page = "";
	                eserializer.family = _page.getPageName() == null ? "" : _page.getPageName();
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
	                    metaserializer.element = e.getElementName().replace("\"", "").trim();
	                    eserializer.elementMetas.add(metaserializer);
	                    eserializer.description = "xxxx";
			    	}
			    	pserializer.elements.add(eserializer);			    	
			    }
			    shserializer.pages.add(pserializer);
			}
			serializer = (T) shserializer;
		}
		else
		{
			pserializer = new PageSerializer();
			pserializer.pageName = page.getPageName() == null ? "" : page.getPageName();
			pserializer.familyName = "";
			pserializer.URL = page.getUrl();
			pserializer.skin = Skin;
		    pserializer.screenShot = "";
		    pserializer.createBy = "azheng";
		    for(String pageFamily : page.getPageFamilies())
		    {
		    	pserializer.families.add(pageFamily);
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
                    metaserializer.element = e.getElementName().replace("\"", "").trim();
                    eserializer.elementMetas.add(metaserializer);
                    eserializer.description = "xxxx";
		    	}
		    	pserializer.elements.add(eserializer);
		    }
		    serializer = (T) pserializer;
		}
		
		return serializer;
	}
	
	
	
	@Deprecated
	public PageSerializer serialzePage(Page page)
	{
		PageSerializer pserializer = null;
		SharedElementSerializer shserializer = new SharedElementSerializer();
		if(page.getPageName().equals(Page.SharedElements))
		{
			for(Page _page : page.getFamilies())
			{
				pserializer = new PageSerializer();
				pserializer.pageName = "";
				pserializer.URL = "";
				pserializer.familyName = _page.getPageName() == null ? "" : _page.getPageName();
				pserializer.skin = "1";
			    pserializer.screenShot = "";
			    pserializer.createBy = "azheng";
			    for(String pageFamily : _page.getPageFamilies())
			    {
			    	pserializer.families.add(pageFamily);
			    }
			    
			    for(Element e : _page.getElements())
			    {
			    	ElementSerializer eserializer = new ElementSerializer();
	                eserializer.elementName = e.getElementName().replace("\"", "").trim();
	                eserializer.page = "";
	                eserializer.family = _page.getPageName() == null ? "" : _page.getPageName();
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
	                    metaserializer.element = e.getElementName().replace("\"", "").trim();
	                    eserializer.elementMetas.add(metaserializer);
	                    eserializer.description = "xxxx";
			    	}
			    	pserializer.elements.add(eserializer);			    	
			    }
			    shserializer.pages.add(pserializer);
			}
		}
		else
		{
			pserializer = new PageSerializer();
			pserializer.pageName = page.getPageName() == null ? "" : page.getPageName();
			pserializer.familyName = "";
			pserializer.URL = page.getUrl();
			pserializer.skin = "1";
		    pserializer.screenShot = "";
		    pserializer.createBy = "azheng";
		    for(String pageFamily : page.getPageFamilies())
		    {
		    	pserializer.families.add(pageFamily);
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
                    metaserializer.element = e.getElementName().replace("\"", "").trim();
                    eserializer.elementMetas.add(metaserializer);
                    eserializer.description = "xxxx";
		    	}
		    	pserializer.elements.add(eserializer);
		    }
		    
		}
		return pserializer;
		
	}
}
