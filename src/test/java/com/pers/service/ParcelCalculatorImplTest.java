package com.pers.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pers.model.MonetaryAmount;
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
	
	@Test
	public void shouldCalculateHeavyParcel() throws Throwable {
		ParcelRequest request = new ParcelRequest();
		request.setWeight(11);
		MonetaryAmount amount = calc.calculate(request);
		Assertions.assertEquals("PHP", amount.getCurrencyCode());
		Assertions.assertEquals(new BigDecimal(220), amount.getAmount());
	}
	
	@Test
	public void shouldCalculateSmallParcel() throws Throwable {
		ParcelRequest request = new ParcelRequest();
		request.setHeight(10);
		request.setWidth(10);
		request.setLength(10);
		MonetaryAmount amount = calc.calculate(request);
		Assertions.assertEquals("PHP", amount.getCurrencyCode());
		Assertions.assertEquals(new BigDecimal(30), amount.getAmount());
	}
	
	@Test
	public void shouldCalculateMediumParcel() throws Throwable {
		ParcelRequest request = new ParcelRequest();
		request.setHeight(10);
		request.setWidth(10);
		request.setLength(20);
		MonetaryAmount amount = calc.calculate(request);
		Assertions.assertEquals("PHP", amount.getCurrencyCode());
		Assertions.assertEquals(new BigDecimal(80), amount.getAmount());
	}
	
	@Test
	public void shouldCalculateLargeParcel() throws Throwable {
		ParcelRequest request = new ParcelRequest();
		request.setHeight(10);
		request.setWidth(20);
		request.setLength(30);
		MonetaryAmount amount = calc.calculate(request);
		Assertions.assertEquals("PHP", amount.getCurrencyCode());
		Assertions.assertEquals(new BigDecimal(300), amount.getAmount());
	}
}
