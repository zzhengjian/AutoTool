package com.gd.misc.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.gd.rest.CardFinderResponseHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class RestTest {
	public final static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet("http://127.0.0.1:8000/pm-cw/get-skins");
			 
			System.out.println("Triggering request " + httpget.getRequestLine());
			 
			// Custom Response Handler
			ResponseHandler<String> responseHandler = new CardFinderResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println(responseBody);
			JsonParser parser = new JsonParser();
			JsonObject o = parser.parse(responseBody).getAsJsonObject();
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
			if(o.has("errorReason"))
				System.out.println(o.get("errorReason"));
			
			
			

		
		} finally {
			//close the connection. Make Sure you do this.
			httpclient.close();
		}
		

	}
	

	public void whenPostRequestUsingHttpClient_thenCorrect() 
			  throws ClientProtocolException, IOException {
			    CloseableHttpClient client = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost("http://www.example.com");
			 
			    String json = "{\"id\":1,\"name\":\"John\"}";
			    StringEntity entity = new StringEntity(json);
			    httpPost.setEntity(entity);
			    httpPost.setHeader("Accept", "application/json");
			    httpPost.setHeader("Content-type", "application/json");
			    			 
			    CloseableHttpResponse response = client.execute(httpPost);
			    System.out.println(response.getStatusLine().getStatusCode());
			    client.close();
			}

}




