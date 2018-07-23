package com.shoppingcart.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4145574083833380135L;
	private String resourceId;

	public UserNotFoundException(String resourceId, String message) {
		super(message);
		this.resourceId = resourceId;

	}

}
