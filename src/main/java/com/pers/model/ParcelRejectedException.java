package com.pers.model;

public class ParcelRejectedException extends Throwable {

	private static final long serialVersionUID = 3892599958222796195L;

	public ParcelRejectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParcelRejectedException(String message) {
		super(message);
	}
	
	

}
