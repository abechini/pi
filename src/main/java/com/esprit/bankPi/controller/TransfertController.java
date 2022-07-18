package com.esprit.bankPi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.resources.ITransfertService;

@Controller(value = "transfertController")
public class TransfertController {

	@Autowired
	ITransfertService transfertService;

	@PostMapping(path = "/transfert", produces = "application/json")
	@ResponseBody
	public TransfertPojo makeTransfert(@RequestBody double amount, @RequestBody CurrencyEnum currency,
			@RequestBody String receiver, @RequestBody String sender, @RequestBody String npl)
			throws TransactionException {
		return transfertService.makeTransfert(amount, currency, receiver, sender, npl);
	}

	@GetMapping(path = "/getAllTransfert/{idCompte}", produces = "application/json")
	@ResponseBody
	public List<TransfertPojo> getAllTransfert(@PathVariable("idCompte") long idCompte) throws TransactionException {
		return transfertService.getByCompte(idCompte);
	}
	
}
