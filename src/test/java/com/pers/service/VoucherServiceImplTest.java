package com.pers.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pers.model.Voucher;

@ExtendWith(MockitoExtension.class)
public class VoucherServiceImplTest {

	@Mock
	public VoucherFeignClient voucherFeignClient;
	
	VoucherServiceImpl service;
	
	@BeforeEach
	public void setup() {
		service = new VoucherServiceImpl(voucherFeignClient);
	}
	
	@Test
	public void shouldReturnVoucher() {
		
		Voucher voucher = new Voucher();
		voucher.setCode("MYNT");
		voucher.setDiscount(new BigDecimal(10));
		voucher.setExpiryDate(LocalDate.now().plusYears(1));
		
		Mockito.when(voucherFeignClient.retrieveVoucher("MYNT", "apikey")).thenReturn(voucher);
		Assertions.assertNotNull(service.retrieveVoucher("MYNT"));
	}
	
	@Test
	public void shouldReturnNull() {
		
		Voucher voucher = new Voucher();
		voucher.setCode("MYNT");
		voucher.setDiscount(new BigDecimal(10));
		voucher.setExpiryDate(LocalDate.now().plusYears(1));
		
		Mockito.when(voucherFeignClient.retrieveVoucher("test", "apikey")).thenReturn(null);
		
		Assertions.assertNull(service.retrieveVoucher("test").getDiscount());
	}
}
