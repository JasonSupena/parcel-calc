package com.pers.service;

import org.springframework.stereotype.Service;

import com.pers.model.MonetaryAmount;
import com.pers.model.ParcelRejectedException;
import com.pers.model.ParcelRequest;

@Service
public class ParcelCalculatorImpl implements ParcelCalculatorApi {

	@Override
	public MonetaryAmount calculate(ParcelRequest request) throws ParcelRejectedException {
		if(request.getWeight() > 50) {
			throw new ParcelRejectedException("Parcel weight is too heavy");
		}
		return new MonetaryAmount();
	}

}
