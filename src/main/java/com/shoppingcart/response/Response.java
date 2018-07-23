package com.shoppingcart.response;

public class Response {
	private Integer code;
	private String message;
	private Object response;

	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + ", response=" + response + "]";
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(int i) {
		this.code = i;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Response(Integer code, String message, Object response) {
		super();
		this.code = code;
		this.message = message;
		this.response = response;
	}

	public Response() {

	}

}
