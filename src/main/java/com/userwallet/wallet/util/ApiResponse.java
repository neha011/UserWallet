package com.userwallet.wallet.util;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

	private HttpStatus status;
	private String message;
	private T body;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public ApiResponse(HttpStatus status, String message, T body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

}
