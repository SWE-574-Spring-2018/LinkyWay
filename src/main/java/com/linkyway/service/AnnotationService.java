package com.linkyway.service;


import com.linkyway.model.domain.AnnotationText;
import com.linkyway.model.domain.HttpRequestResult;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Transactional
@Service
public class AnnotationService{
	@Value("${annotationserver.url}")
	public String API_URL;
	@Value("${annotationserver.containerid}")
	public String Container_ID;
	@Value("${linkyway.url}")
	public String Linkyway_URL;
	
	public HttpRequestResult<String> CreateAnnotation(AnnotationText textDTO) throws URISyntaxException, IOException {
		final String AnnotationURL = getAnnotationUri(null).toString();
		HttpRequestResult<String> requestResult = new HttpRequestResult<>();
		final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost postRequest = new HttpPost(AnnotationURL);
			postRequest.addHeader("Accept", "application/ld+json; profile=\"http://www.w3.org/ns/anno.jsonld\"");
			postRequest.addHeader("Content-Type", "application/ld+json; profile=\"http://www.w3.org/ns/anno.jsonld\"");
			StringEntity jsonEntity = new StringEntity(textDTO.toJsonLD());
			postRequest.setEntity(jsonEntity );
			
			HttpResponse response = httpClient.execute(postRequest);
			requestResult.setHttpStatusCode(response.getStatusLine().getStatusCode());
			if (requestResult.getHttpStatusCode() == 201) {
				final JSONObject jsonObject = parseResponseToJsonObject(response);
				String annotationId = jsonObject.getString("id");
				
				requestResult.setErrorMessage("");
				requestResult.setHasError(false);
				requestResult.setData(annotationId);
			} else {
				requestResult.setData(null);
			}
			
			httpClient.close();
		} catch (Exception e) {
			httpClient.close();
			requestResult.setData(null);
			requestResult.setErrorMessage(e.getMessage());
			requestResult.setHasError(true);
		}
		
		return requestResult;
	}
	
	public HttpRequestResult<AnnotationText> getAnnotation(String annotationId) throws IOException, URISyntaxException {
		final String AnnotationURL = getAnnotationUri(annotationId).toString();
		HttpRequestResult<AnnotationText> requestResult = new HttpRequestResult<>();
		final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpGet getRequest = new HttpGet(AnnotationURL);
			getRequest.addHeader("Accept", "application/ld+json; profile=\"http://www.w3.org/ns/anno.jsonld\"");
			HttpResponse response = httpClient.execute(getRequest);
			
			requestResult.setErrorMessage("");
			requestResult.setHasError(false);
			requestResult.setHttpStatusCode(response.getStatusLine().getStatusCode());
			
			if (requestResult.getHttpStatusCode() == 200) {
				final JSONObject jsonObject = parseResponseToJsonObject(response);
				requestResult.setData(new AnnotationText().mapFrom(jsonObject));
			} else {
				requestResult.setData(null);
			}
			
			httpClient.close();
		} catch (Exception e) {
			httpClient.close();
			requestResult.setData(null);
			requestResult.setErrorMessage(e.getMessage());
			requestResult.setHasError(true);
		}
		
		return requestResult;
	}
	
	public HttpRequestResult<ArrayList<AnnotationText>> getAnnotations(String target) throws IOException, URISyntaxException {
		final String AnnotationURL = getSearchUri(target).toString();
		HttpRequestResult<ArrayList<AnnotationText>> requestResult = new HttpRequestResult<>();
		final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpGet getRequest = new HttpGet(AnnotationURL);
			getRequest.addHeader("Accept", "application/ld+json; profile=\"http://www.w3.org/ns/anno.jsonld\"");
			HttpResponse response = httpClient.execute(getRequest);
			
			requestResult.setErrorMessage("");
			requestResult.setHasError(false);
			requestResult.setHttpStatusCode(response.getStatusLine().getStatusCode());
			
			if (requestResult.getHttpStatusCode() == 200) {
				ArrayList<AnnotationText> annotations = new ArrayList<>();
				final JSONObject jsonObject = parseResponseToJsonObject(response);
				final JSONObject firstResults = jsonObject.getJSONObject("first");
				JSONArray annotationsArray;
				if(firstResults.has("items")){
					annotationsArray=firstResults.getJSONArray("items");
				}else{
					annotationsArray=firstResults.getJSONObject("as:items").getJSONArray("@list");
				}
				
				for (int i=0; i < annotationsArray.length(); i++) {
					JSONObject annotation=annotationsArray.getJSONObject(i);
					annotations.add(new AnnotationText().mapFrom(annotation));
				}
				
				requestResult.setData(annotations);
			} else {
				requestResult.setData(null);
			}
			
			httpClient.close();
		} catch (Exception e) {
			httpClient.close();
			requestResult.setData(null);
			requestResult.setErrorMessage(e.getMessage());
			requestResult.setHasError(true);
		}
		
		return requestResult;
	}
	
	private String parseResponseToString(HttpResponse response) throws IOException {
		return IOUtils.toString(response.getEntity().getContent());
	}
	
	private JSONObject parseResponseToJsonObject(HttpResponse response) throws IOException {
		String responseBody = parseResponseToString(response);
		return new JSONObject(responseBody);
	}
	
	private JSONArray parseResponseToJsonArray(HttpResponse response) throws IOException {
		String responseBody = parseResponseToString(response);
		return new JSONArray(responseBody);
	}
	
	public URI getSearchUri(String target) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(API_URL);
		final URI uri = uriBuilder.setPath(uriBuilder.getPath()+"/w3c/services/search/target")
				.addParameter("fields", "source")
				.addParameter("value", target)
				.addParameter("strict", "false")
				.build();
		
		return uri;
	}
	
	public URI getAnnotationUri(String id) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(API_URL);
		String pathSegment = uriBuilder.getPath()+ "/w3c/" + Container_ID + "/";
		if (id != null && !id.equals("")) {
			pathSegment = pathSegment + id;
		}
		final URI uri = uriBuilder.setPath(pathSegment).build();
		
		return uri;
	}
}
