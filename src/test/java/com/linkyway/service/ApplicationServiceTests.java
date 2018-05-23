package com.linkyway.service;

import com.linkyway.model.domain.AnnotationText;
import com.linkyway.model.domain.HttpRequestResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ApplicationServiceTests{
	AnnotationService service;
	
	public ApplicationServiceTests() {
		this.service=new AnnotationService();
		this.service.Linkyway_URL="http://www.linkyway.com";
		this.service.Container_ID="a027af17-5119-4e60-8286-c63bf87b50b7";
		this.service.API_URL="http://localhost:8090/annotation";
	}
	
	@Test
	public void getAnnotation() throws IOException, URISyntaxException {
		//final HttpRequestResult<AnnotationText> annotation = service.getAnnotation("568b0148-493c-4004-85f7-fafbd1b5ad8c");
		
		//Assert.assertEquals(200,annotation.getHttpStatusCode());
	}
	
	@Test
	public void getAnnotations() throws IOException, URISyntaxException {
		//final HttpRequestResult<ArrayList<AnnotationText>> annotations = service.getAnnotations("https://twitter.com/realDonaldTrump/status/998256454590193665");
		
		//Assert.assertEquals(200,annotations.getHttpStatusCode());
	}
	
	@Test
	public void createAnnotation() throws IOException, URISyntaxException {
		//final AnnotationText annotationText = new AnnotationText(
		//		"https://twitter.com/realDonaldTrump/status/998256454590193665",
		//		"998256454590193665",
		//		"I hereby demand, and will do so officially tomorrow, that the Department of Justice look into whether or not the FBI/DOJ infiltrated or surveilled the Trump Campaign for Political Purposes - and if any such demands or requests were made by people within the Obama Administration!",
		//		"All",
		//		0,
		//		0,
		//		"Test");
		//final HttpRequestResult<String> stringHttpRequestResult = service.CreateAnnotation(annotationText);
		
		//Assert.assertEquals(201,stringHttpRequestResult.getHttpStatusCode());
	}
}
