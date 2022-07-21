package com.esprit.bankPi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.resources.IWithdrowService;

@Controller(value = "withdrowController")
public class WithdrowController {

	@Autowired
	IWithdrowService withdrowService;

	@PostMapping(path = "/withdraw/{amount}/{rib}", produces = "application/json")
	@ResponseBody
	public WithdrowPojo makeWithdraw(@PathVariable("amount") double amount, @PathVariable("rib") String rib)
			throws TransactionException {
		return withdrowService.makeWithdraw(rib, amount);
	}

	@GetMapping(path = "/getAllWithdraw/{idCompte}", produces = "application/json")
	@ResponseBody
	public List<WithdrowPojo> getAllWithdraw(@PathVariable("idCompte") long idCompte) throws TransactionException {
		return withdrowService.getAllWithdraw(idCompte);
	}

}
