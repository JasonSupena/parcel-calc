package com.pers.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Voucher {

	public String code;
	public BigDecimal discount;
	public LocalDate expiry;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public LocalDate getExpiry() {
		return expiry;
	}
	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}
	
}
