package com.gd.misc.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class RestTest {
	public final static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
		HttpGet httpget = new HttpGet("http://gdcloadperf05:7000/cardfinderservice/CardFinderService.svc/get?product=7502&environment=QA4&value=20");
		 
		System.out.println("Triggering request " + httpget.getRequestLine());
		 
		// Custom Response Handler
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		 
			public String handleResponse(final HttpResponse response)
				throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity): null;
				}
				else if(status == 404)
				{
					HttpEntity entity = response.getEntity();
					
					return entity != null ? EntityUtils.toString(entity): null;
				}
				else {
				throw new ClientProtocolException(
						"Unexpected response status: " + status);
				}
			}
		 
		};
		String responseBody = httpclient.execute(httpget, responseHandler);
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
}