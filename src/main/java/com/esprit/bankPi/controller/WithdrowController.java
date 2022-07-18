package com.esprit.bankPi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.resources.IWithdrowService;

@Controller(value = "withdrowController")
public class WithdrowController {

	@Autowired
	IWithdrowService withdrowService;

	@PostMapping(path = "/withdraw", produces = "application/json")
	@ResponseBody
	public WithdrowPojo makeWithdraw(@RequestBody double amount, @RequestBody long compteId)
			throws TransactionException {
		return withdrowService.makeWithdraw(compteId, amount);
	}

	@GetMapping(path = "/getAllWithdraw/{idCompte}", produces = "application/json")
	@ResponseBody
	public List<WithdrowPojo> getAllWithdraw(@PathVariable("idCompte") long idCompte) throws TransactionException {
		return withdrowService.getAllWithdraw(idCompte);
	}

}
