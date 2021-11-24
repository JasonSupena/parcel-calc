package com.pers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pers.model.ParcelRejectedException;
import com.pers.model.ParcelRequest;

@ExtendWith(MockitoExtension.class)
public class ParcelCalculatorImplTest {

	private ParcelCalculatorImpl calc;
	
	
	@BeforeEach
	public void setup()	{
		calc = new ParcelCalculatorImpl();
	}
	
	@Test
	public void shouldReturnMonetaryAmount() throws ParcelRejectedException {
		ParcelRequest request = new ParcelRequest();
		Assertions.assertNotNull(calc.calculate(request));
	}
	
	@Test
	public void shouldThrowParcelRejectedException() {
		Assertions.assertThrows(ParcelRejectedException.class, () -> {
			ParcelRequest request = new ParcelRequest();
			request.setWeight(51);
			calc.calculate(request);
		});
	}
}
