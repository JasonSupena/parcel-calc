package com.pers.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pers.model.Voucher;

@FeignClient(value = "voucherService", url = "https://mynt-exam.mocklab.io/")
public interface VoucherFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/voucher/{voucherCode}")
	public Voucher retrieveVoucher(@PathVariable("voucherCode") String voucherCode, @RequestParam String key);
}
