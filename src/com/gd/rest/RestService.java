package com.gd.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RestService {
	
	private String cardNumber;
	private String cvv;
	private String expDate;
	private String pinValue;
	public final static String DefaultValue = "20";
	
	
	public RestService(String serviceType)
	{
		init(serviceType);		
	}
	
	
	private void init(String serviceType) {
		
		switch(Enum.valueOf(Service.class, serviceType))
		{
			case PinGrab:
				break;
				
			default:
				break;
		}

			
	}

	public void makeRequest(String productCode, String env)
	{
		
		try {
			makeRequest(productCode, env, DefaultValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JsonObject makeRequest(String productCode, String env, String value) throws IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		JsonObject oJson = null;
		try {

			URIBuilder uri = new URIBuilder("http://gdcloadperf05:7000/cardfinderservice/CardFinderService.svc/get").addParameter("product", productCode).addParameter("environment", env).addParameter("value", value);
			HttpGet httpget = new HttpGet(uri.build());		
			
			System.out.println("Triggering request " + httpget.getRequestLine());
			 
			// Custom Response Handler
			ResponseHandler<String> responseHandler = new DefaultResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			JsonParser parser = new JsonParser();
			oJson = parser.parse(responseBody).getAsJsonObject();

//			System.out.println("----------------------------------------");
//			System.out.println(responseBody);
//			if(o.has("errorReason"))
//				System.out.println(o.get("errorReason"));
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			//close the connection. Make Sure you do this.
			httpclient.close();
		}
		return oJson;		
	}

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardnumber) {
		this.cardNumber = cardnumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getPinValue() {
		return pinValue;
	}
	public void setPinValue(String pinValue) {
		this.pinValue = pinValue;
	}
	
	
	public enum Service
	{
		PinGrab,
		CardFinder
	}

}
