package com.gd.misc.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.google.gson.JsonObject;
public class RestTest {
//	public final static void main(String[] args) throws Exception {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		try {
//			HttpGet httpget = new HttpGet("http://127.0.0.1:8000/pm-cw/get-skins");
//			 
//			System.out.println("Triggering request " + httpget.getRequestLine());
//			 
//			// Custom Response Handler
//			ResponseHandler<String> responseHandler = new DefaultResponseHandler();
//			String responseBody = httpclient.execute(httpget, responseHandler);
//			System.out.println(responseBody);
//			JsonParser parser = new JsonParser();
//			JsonObject o = parser.parse(responseBody).getAsJsonObject();
//			System.out.println("----------------------------------------");
//			System.out.println(responseBody);
//	
//			
//
//		
//		} finally {
//			//close the connection. Make Sure you do this.
//			httpclient.close();
//		}
//		
//
//	}
	
	@Test
	public void whenPostRequestUsingHttpClient_thenCorrect() 
			  throws ClientProtocolException, IOException {
			    CloseableHttpClient client = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost("https://www.rushcardlive.com/prepaid/activation/online-activation-initial");
			 
			    String json = "{\"id\":1,\"name\":\"John\"}";
			    JsonObject formdata = new JsonObject();
			    formdata.addProperty("__RequestVerificationToken", "ka3XN1mReATOZIkHOldmnE4LhCvuItjl5bgozUbx2Fr5O9eCUMqbnZsyFGKSORHIeFgPjnEfJO1qb0rQKZksVrp6tPYXuVbA1tiavJPeVLmvh6mkbJPanaZ7LgSJT3zrWpZRiQ2");
			    formdata.addProperty("CardInfo_AccountNo", "5018462321739853");
			    formdata.addProperty("CardInfo_CVV", "627");
			    StringEntity entity = new StringEntity(formdata.toString());
			    httpPost.setEntity(entity);
			    httpPost.setHeader("Accept", "application/json");
			    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			    			 
			    CloseableHttpResponse response = client.execute(httpPost);
			    System.out.println(response.getStatusLine().getStatusCode());
			    client.close();
			}

}




