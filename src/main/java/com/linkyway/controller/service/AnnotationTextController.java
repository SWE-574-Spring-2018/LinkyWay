package com.linkyway.controller.service;

import com.linkyway.model.domain.AnnotationText;
import com.linkyway.model.domain.HttpRequestResult;
import com.linkyway.service.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;


/**
 * @author duran.serkan.kilic
 */
@Controller
@RequestMapping("/linkyway/api/annotationtext")
public class AnnotationTextController{
	@Autowired
	private AnnotationService annotationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAnnotations(@NotNull String target) {
		try {
			final HttpRequestResult<ArrayList<AnnotationText>> httpRequestResult = annotationService.getAnnotations(target);
			if (httpRequestResult.getHttpStatusCode() == 200) {
				return ResponseEntity.status(HttpStatus.OK).body(httpRequestResult.getData());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpRequestResult.getErrorMessage());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity getAnnotation(@NotNull @PathVariable String id) {
		try {
			final HttpRequestResult<AnnotationText> httpRequestResult = annotationService.getAnnotation(id);
			if (httpRequestResult.getHttpStatusCode() == 200) {
				return ResponseEntity.status(HttpStatus.OK).body(httpRequestResult.getData());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpRequestResult.getErrorMessage());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity createAnnotation(@RequestBody AnnotationText annotationText) {
		
		if (annotationText == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(annotationText);
		}
		try {
			final HttpRequestResult<String> httpRequestResult = annotationService.CreateAnnotation(annotationText);
			if (httpRequestResult.getHttpStatusCode() == 201) {
				return ResponseEntity.status(HttpStatus.CREATED).body(httpRequestResult.getData());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
