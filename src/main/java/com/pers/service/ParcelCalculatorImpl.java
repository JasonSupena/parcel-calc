package com.pers.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pers.model.MonetaryAmount;
import com.pers.model.ParcelRejectedException;
import com.pers.model.ParcelRequest;
import com.pers.model.Voucher;

@Service
public class ParcelCalculatorImpl implements ParcelCalculatorApi {

	private VoucherServiceApi voucherService;
	
	@Autowired
	public ParcelCalculatorImpl(VoucherServiceApi voucherService) {
		this.voucherService = voucherService;
	}

	@Override
	public MonetaryAmount calculate(ParcelRequest request) throws ParcelRejectedException {
		MonetaryAmount parcelAmount = new MonetaryAmount();
		Voucher voucher = retrieveVoucher(request);
		
		int volume = request.getHeight() * request.getWidth() * request.getLength();
		int parcelWeight = request.getWeight();
		
		if(parcelWeight > 50) {
			throw new ParcelRejectedException("Parcel weight is too heavy");
		}
		
		if(parcelWeight > 10 && parcelWeight <= 50) {
			computeParcelAmountBasedOnVolume(parcelAmount, voucher, parcelWeight, BigDecimal.valueOf(20));
			return parcelAmount;
		}
		
		if(volume < 1500) {
			computeParcelAmountBasedOnVolume(parcelAmount, voucher, volume, BigDecimal.valueOf(.03));
			return parcelAmount;
		}
		
		if(volume > 1500 && volume < 2500) {
			computeParcelAmountBasedOnVolume(parcelAmount, voucher, volume, BigDecimal.valueOf(.04));
			return parcelAmount;
		}
		
		computeParcelAmountBasedOnVolume(parcelAmount, voucher, volume, BigDecimal.valueOf(.05));
		
		return parcelAmount;
	}

	private Voucher retrieveVoucher(ParcelRequest request) {
		if(request.getVoucherCode() != null) {
			return voucherService.retrieveVoucher(request.getVoucherCode());
		}
		return null;
	}

	private void computeParcelAmountBasedOnVolume(MonetaryAmount parcelAmount, Voucher voucher, int baseValue, BigDecimal costMultiplier) {
		parcelAmount.setAmount(computeForDiscount(BigDecimal.valueOf(baseValue).multiply(costMultiplier), voucher));
		parcelAmount.setCurrencyCode("PHP");
	}

	private BigDecimal computeForDiscount(BigDecimal partialAmount, Voucher voucher) {
		Optional<Voucher> optionalVoucher = Optional.ofNullable(voucher);
		BigDecimal discount = optionalVoucher.map(Voucher::getDiscount).orElse(BigDecimal.ZERO);
		return partialAmount.subtract(discount);
	}

}
