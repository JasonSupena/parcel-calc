package com.pers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pers.model.MonetaryAmount;
import com.pers.model.ParcelRejectedException;
import com.pers.model.ParcelRequest;
import com.pers.service.ParcelCalculatorApi;

@RestController
public class ParcelCalculatorController {

	@Autowired
	private ParcelCalculatorApi calc;
	
	@PostMapping(value = "/api/parcel", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> calculateParcel(@RequestBody ParcelRequest request) throws ParcelRejectedException {
		MonetaryAmount amount = calc.calculate(request);
		return new ResponseEntity<Object>(amount, HttpStatus.OK);
	}
}
