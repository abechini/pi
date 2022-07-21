package com.esprit.bankPi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.jobs.BankFeesJob;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transactionController")
@JsonIgnoreProperties(ignoreUnknown=true)
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.repository.DepositRepository;
import com.esprit.bankPi.repository.WithdrowRepository;
import com.esprit.bankPi.resources.TransactionServiceImpl;

@Controller(value = "TransactionController")
@RequestMapping("/api/transactions")
public class TransactionController {
	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	@Autowired
	BankFeesJob bankFeesJob;
	
	@GetMapping(path = "/executeJob", produces = "application/json")
	@ResponseBody
	public void getAllDeposit() {
		try {
		 bankFeesJob.myfirstJob();
	    } catch(Exception e) {
	    		System.out.println(e);
	    }
	}

	@GetMapping(value = "/exportExcel/{idCompte}")
	public ResponseEntity exportExcel(@PathVariable long idCompte) {
		transactionServiceImpl.exportExtrait(idCompte);
		return ResponseEntity.status(HttpStatus.OK).body("transactions exported successfully");
	}

}
