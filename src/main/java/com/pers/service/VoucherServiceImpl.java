package com.pers.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pers.model.Voucher;

@Service
public class VoucherServiceImpl implements VoucherServiceApi {

	private VoucherFeignClient voucherFeignClient;
	
	@Autowired
	public VoucherServiceImpl(VoucherFeignClient voucherFeignClient) {
		this.voucherFeignClient = voucherFeignClient;
	}

	@Override
	public Voucher retrieveVoucher(String voucherCode) {
		final Voucher voucher = new Voucher();
		Optional.ofNullable(voucherCode).ifPresent(vc -> {
			Optional.ofNullable(voucherFeignClient.retrieveVoucher(voucherCode, "apikey")).ifPresent(v -> {
				voucher.setCode(v.getCode());
				voucher.setDiscount(v.getDiscount());
				voucher.setExpiry(v.getExpiry());
			});
		});
		
		return voucher;
	}

}
