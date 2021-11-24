package com.pers.service;

import com.pers.model.MonetaryAmount;
import com.pers.model.ParcelRejectedException;
import com.pers.model.ParcelRequest;

public interface ParcelCalculatorApi {

	MonetaryAmount calculate(ParcelRequest request) throws ParcelRejectedException;
}
