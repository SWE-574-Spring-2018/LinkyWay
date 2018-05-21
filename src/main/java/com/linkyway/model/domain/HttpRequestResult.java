package com.linkyway.model.domain;

public class HttpRequestResult<T>
{
	private boolean HasError;
	private String ErrorMessage;
	private int HttpStatusCode;
	private T Data;
	
	public boolean isHasError() {
		return HasError;
	}
	
	public void setHasError(boolean hasError) {
		HasError = hasError;
	}
	
	public String getErrorMessage() {
		return ErrorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	public int getHttpStatusCode() {
		return HttpStatusCode;
	}
	
	public void setHttpStatusCode(int httpStatusCode) {
		HttpStatusCode = httpStatusCode;
	}
	
	public T getData() {
		return Data;
	}
	
	public void setData(T data) {
		Data = data;
	}
}
