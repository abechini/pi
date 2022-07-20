package com.esprit.bankPi.currencyConverter;

import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {
	
	@Autowired
	private CurrencyConverterRepository accountPromotionsRespository;

	public MonetaryAmount convert(String currentCurrency, String targetCurrency, BigDecimal amount) {
		// According to the currency code to get the currency unit
		CurrencyUnit currentCurrencyUnit = Monetary.getCurrency(currentCurrency);
		CurrencyUnit targetCurrencyUnit = Monetary.getCurrency(targetCurrency);

		MonetaryAmount current = Monetary.getDefaultAmountFactory().setCurrency(currentCurrencyUnit).setNumber(amount)
				.create();
		CurrencyConversion conversion = MonetaryConversions.getConversion(targetCurrencyUnit);
		MonetaryAmount convertedAmount = current.with(conversion);
		return convertedAmount;

	}

}
