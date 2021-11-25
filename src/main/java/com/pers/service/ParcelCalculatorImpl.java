package com.pers.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.pers.model.MonetaryAmount;
import com.pers.model.ParcelRejectedException;
import com.pers.model.ParcelRequest;

@Service
public class ParcelCalculatorImpl implements ParcelCalculatorApi {

	@Override
	public MonetaryAmount calculate(ParcelRequest request) throws ParcelRejectedException {
		MonetaryAmount parcelAmount = new MonetaryAmount();
		int volume = request.getHeight() * request.getWidth() * request.getLength();
		int parcelWeight = request.getWeight();
		
		if(parcelWeight > 50) {
			throw new ParcelRejectedException("Parcel weight is too heavy");
		}
		
		if(parcelWeight > 10 && parcelWeight <= 50) {
			parcelAmount.setAmount(new BigDecimal(20 * parcelWeight));
			parcelAmount.setCurrencyCode("PHP");
			return parcelAmount;
		}
		
		if(volume < 1500) {
			parcelAmount.setAmount(new BigDecimal(.03 * volume));
			parcelAmount.setCurrencyCode("PHP");
			return parcelAmount;
		}
		
		if(volume > 1500 && volume < 2500) {
			parcelAmount.setAmount(new BigDecimal(.04 * volume));
			parcelAmount.setCurrencyCode("PHP");
			return parcelAmount;
		}
		
		parcelAmount.setAmount(new BigDecimal(.05 * volume));
		parcelAmount.setCurrencyCode("PHP");
		
		return parcelAmount;
	}

}
