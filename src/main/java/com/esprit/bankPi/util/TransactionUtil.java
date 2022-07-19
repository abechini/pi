package com.esprit.bankPi.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.bankPi.CurrencyConverter.CurrencyConverterService;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CurrencyEnum;

public class TransactionUtil {

	@Autowired
	static CurrencyConverterService converterService;

	public static boolean acceptNegative(Compte c, double amount) {
		return c.getSolde() - amount >= c.getNegativeCeiling();
	}

	public static double getRealAmount(double amount, CurrencyEnum currency, CurrencyEnum clientCurrency) {
		if (!currency.equals(CurrencyEnum.TND)) {
			amount = converterService.convert(currency.toString(), clientCurrency.toString(), new BigDecimal(amount))
					.getNumber().doubleValue();
		}
		return amount;
	}

}
