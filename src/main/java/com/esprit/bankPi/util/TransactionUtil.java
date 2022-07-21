package com.esprit.bankPi.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.bankPi.currencyConverter.CurrencyConverterService;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CurrencyEnum;

public class TransactionUtil {

	@Autowired
	static CurrencyConverterService converterService = new CurrencyConverterService();

	public static boolean acceptNegative(Compte c, double amount) {
		return c.getSolde() - amount >= c.getNegativeCeiling();
	}

	public static double getRealAmount(double amount, CurrencyEnum currency, CurrencyEnum clientCurrency) {
		if (!currency.equals(clientCurrency)) {
			amount = converterService.convert(currency.toString(), clientCurrency.toString(), new BigDecimal(amount))
					.getNumber().doubleValue();
		}
		return amount;
	}

	public static boolean hasSuffisantSolde(double amount, double solde, double negativeCeiling) {
		return solde - amount + negativeCeiling >= 0;
	}
	
	public static Compte distractBankFees(Compte c) {
		c.setSolde(c.getSolde() - c.getAcountFees());
		return c;
	}

}
