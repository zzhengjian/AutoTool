package com.gd.pages.serializer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gd.common.ConverterSettings;
import com.gd.rest.DefaultResponseHandler;
import com.google.gson.Gson;


public class PageParser {
	
	private static final Logger logger = LoggerFactory.getLogger(PageParser.class);
	
	public static String Skin = "1";
	private boolean convertOn = true;
	private List<File> filelist = new ArrayList<File>();
	private static boolean stop = false;
	
	public void turnOffConvert()
	{
		convertOn = false;		
	}
	
	public static void stopParsing()
	{
		stop = true;		
	}
	
	public void createSkin(String skinName)
	{
		String json = null;
		
		SkinSerializer sSerializer = new SkinSerializer();
		sSerializer.project = "8";
		sSerializer.skinName = skinName;		
		
		json = new Gson().toJson(sSerializer);
		try {
			whenPostJsonRequestUsingHttpClient(json, getCreateSkinEndpoint());
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}		
	}
	
	public String getCreateSkinEndpoint() {
		String url = null;
		try {
			url = new URIBuilder(ConverterSettings.EndPoint).setPath("/writer/pm-cw/skin_create/").toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		return url;
	}
	
	public String getSavePageEndpoint() {
		String url = null;
		try {
			url = new URIBuilder(ConverterSettings.EndPoint).setPath("/writer/pm-cw/savepage/").toString();
			logger.trace("savepage endpoint: {}", url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		return url;
	}

	public String getSaveFamilyEndpoint() {
		String url = null;
		try {
			url = new URIBuilder(ConverterSettings.EndPoint).setPath("/writer/pm-cw/savefamily/").toString();
			logger.trace("savefamily endpoint: {}", url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		return url;
	}


	public void parsePageFolder(String path)
	{
		List<File> pagefiles = listFilesAndFilesSubDirectories(new File(path));
		List<Page> projectPages = new ArrayList<Page>();
		for(File f : pagefiles){
			Page page = new Page();
			page.setPageFile(f);
			page.ProcessPage();
			projectPages.add(page);
		}
		
		String json = null;
		
		List<Page> families = Page.getFamilies();
		for(Page family : families)
		{
			if(stop){
				return;
			}
			json = new Gson().toJson(serializePage(family, FamilySerializer.class));
			logger.trace(json);
			try {
				if(convertOn){
					whenPostStringRequestUsingHttpClient(json, getSaveFamilyEndpoint());
				} 
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		for(Page page : projectPages){
			if(stop){
				return;
			}
			if(!page.getPageName().equals("")){
				json = new Gson().toJson(serializePage(page, PageSerializer.class));
				logger.trace(json);
				try {
					if(convertOn){
						whenPostStringRequestUsingHttpClient(json, getSavePageEndpoint());
					}
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}	
		}

	}
	
	private List<File> listFilesAndFilesSubDirectories(File fileOrDir) {
		

        //get all the files from a directory
        File[] fList = fileOrDir.listFiles();
        for (File file : fList){
            if (file.isFile() && file.getName().endsWith(".rb")){
            	filelist.add(file);
            } 
            else if (file.isDirectory()){
                listFilesAndFilesSubDirectories(file);
            }
        }
        return filelist;
	
	}
	
	public void updatedParse(File pageFile)
	{
		String json = null;
		Page page = new Page();
		page.setPageFile(pageFile);
		page.ProcessPage();
		

		List<Page> families = Page.getFamilies();
		for(Page family : families)
		{
			json = new Gson().toJson(serializePage(family, FamilySerializer.class));
			logger.trace(json);
			try {
				if(convertOn){
					whenPostStringRequestUsingHttpClient(json, getSaveFamilyEndpoint());
				} 
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		if(!page.getPageName().equals("")){
			json = new Gson().toJson(serializePage(page, PageSerializer.class));
			logger.trace(json);
			try {
				if(convertOn){
					whenPostStringRequestUsingHttpClient(json, getSavePageEndpoint());
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}	

	}
	
	public void whenPostStringRequestUsingHttpClient(String json, String endPoint) 
			  throws ClientProtocolException, IOException {
			    CloseableHttpClient client = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost(endPoint);
			 			    
			    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
				params.add(new BasicNameValuePair("myjsondata", json));
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				String response = client.execute(httpPost,new DefaultResponseHandler());
			    logger.info("server response: {}", response);
			    client.close();
			}
	
	public void whenPostJsonRequestUsingHttpClient(String json, String endPoint) 
			  throws ClientProtocolException, IOException {
			    CloseableHttpClient client = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost(endPoint);
			 
			    StringEntity entity = new StringEntity(json);
			    httpPost.setEntity(entity);
			    httpPost.setHeader("Accept", "application/json");
			    httpPost.setHeader("Content-type", "application/json");
			    			 
			    String response = client.execute(httpPost,new DefaultResponseHandler());
			    logger.info("server response: {}", response);
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
                        metaserializer.platform = meta.getPlatform();;
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
