package com.esprit.bankPi.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.resources.IDepositService;

@RestController
@RequestMapping("/depositController")
public class DepositController {

	@Autowired
	IDepositService depositService;

	@PostMapping(path = "/deposit", produces = "application/json")
	@ResponseBody
	public void makeDeposit(@RequestBody double amount, @RequestBody CurrencyEnum currency, @RequestBody long idCompte)
			throws TransactionException {
		depositService.deposit(amount, currency, idCompte);
	}

	@GetMapping(path = "/getAllDeposit", produces = "application/json")
	@ResponseBody
	public void getAllDeposit(@QueryParam("idCompte") long idCompte) throws TransactionException {
		depositService.getAllDeposit(idCompte);
	}

}
