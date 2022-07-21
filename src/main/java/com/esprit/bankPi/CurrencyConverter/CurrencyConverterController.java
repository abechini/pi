package com.esprit.bankPi.currencyConverter;

import java.math.BigDecimal;

import javax.money.MonetaryAmount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/converter")
public class CurrencyConverterController {
	@Autowired
	CurrencyConverterService currencyConverterService;

	@PostMapping(value = "/convert")
	@ResponseBody
	public MonetaryAmount convert(@RequestParam String currentCurrency, String targetCurrency, BigDecimal amount) {
		return currencyConverterService.convert(currentCurrency, targetCurrency, amount);
	}

}
