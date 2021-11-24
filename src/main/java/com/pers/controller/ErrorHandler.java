package com.pers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pers.model.ParcelRejectedException;

@ControllerAdvice(basePackages = "com.pers.controller")
public class ErrorHandler {

	@ExceptionHandler(value = { ParcelRejectedException.class })
	public ResponseEntity<Object> handleHeaveyParcel(ParcelRejectedException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
