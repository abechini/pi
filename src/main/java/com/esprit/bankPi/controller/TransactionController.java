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
@Slf4j
@RequestMapping("/transactionController")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionController {

	@Autowired
	BankFeesJob bankFeesJob;
	
	@GetMapping(path = "/executeJob", produces = "application/json")
	@ResponseBody
	public void getAllDeposit() {
		try {
		 bankFeesJob.myfirstJob();
	    } catch(Exception e) {
	    	log.info("Exception" + e);
	    }
	}
}
