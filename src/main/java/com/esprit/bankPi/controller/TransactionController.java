package com.esprit.bankPi.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.jobs.BankFeesJob;
import com.esprit.bankPi.resources.TransactionServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/transactionController")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionController {

	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	@Autowired
	BankFeesJob bankFeesJob;

	@GetMapping(path = "/executeJob", produces = "application/json")
	@ResponseBody
	public void lunchDistractBankFeesJob() {
		try {
			bankFeesJob.jobRunner();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@GetMapping(value = "/exportExcel/{idCompte}")
	public ResponseEntity exportExcel(@PathVariable long idCompte) {
		transactionServiceImpl.exportExtrait(idCompte);
		return ResponseEntity.status(HttpStatus.OK).body("transactions exported successfully");
	}


}
