package com.pers.service;

import com.pers.model.Voucher;

public interface VoucherServiceApi {

	Voucher retrieveVoucher(String voucherCode);
	
}
