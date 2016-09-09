package com.gd.rest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;



public class DefaultResponseHandler implements ResponseHandler<String>{

	public String handleResponse(final HttpResponse response) throws IOException {
			//int status = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			return entity != null ? EntityUtils.toString(entity): null;

		}
}