package com.esprit.bankPi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.resources.IDepositService;

@RestController
@RequestMapping("/depositController")
public class DepositController {

	@Autowired
	IDepositService depositService;

	@PostMapping(path = "/deposit/{amount}/{currency}/{rib}", produces = "application/json")
	@ResponseBody
	public DepositPojo makeDeposit(@PathVariable("amount") double amount,
			@PathVariable("currency") CurrencyEnum currency, @PathVariable("rib") String rib)
			throws TransactionException {
		return depositService.deposit(amount, currency, rib);
	}

	@GetMapping(path = "/getAllDeposit/{idCompte}", produces = "application/json")
	@ResponseBody
	public List<DepositPojo> getAllDeposit(@PathVariable("idCompte") long idCompte) throws TransactionException {
		return depositService.getAllDeposit(idCompte);
	}

}
