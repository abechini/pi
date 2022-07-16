package com.esprit.bankPi.CurrencyConverter;
import com.esprit.bankPi.CurrencyConverter.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
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
