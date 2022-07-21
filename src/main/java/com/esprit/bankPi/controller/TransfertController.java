package com.esprit.bankPi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.resources.ITransfertService;

@RestController
@RequestMapping(value = "transfertController")
public class TransfertController {

	@Autowired
	ITransfertService transfertService;

	@PostMapping(path = "/makeTransfert/{idCompte}", produces = "application/json")
	@ResponseBody
	public TransfertPojo makeTransfert(@RequestBody TransfertPojo transfert, @PathVariable("idCompte") long idCompte)
			throws TransactionException {
		return transfertService.makeTransfert(transfert, idCompte);
	}

	@PostMapping(path = "/accpetTransfert", produces = "application/json")
	@ResponseBody
	public TransfertPojo acceptTransfert(@RequestBody TransfertPojo transfert) throws TransactionException {
		return transfertService.accpetTransfert(transfert);
	}

	@GetMapping(path = "/getAllTransfert/{idCompte}", produces = "application/json")
	@ResponseBody
	public List<TransfertPojo> getAllTransfert(@PathVariable("idCompte") long idCompte) throws TransactionException {
		return transfertService.getByCompte(idCompte);
	}

}
