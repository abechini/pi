package com.esprit.bankPi.simulator;
import com.esprit.bankPi.simulator.LoanSimulatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/simulator")
public class LoanSimulatorController {
	
	 @Autowired
	 LoanSimulatorService loanSimulatorService;

	     @PostMapping(value = "/simpleSimulator")
	     @ResponseBody
	     public String simpleLoan(@RequestParam double loanAmount, double intrestRate, double months) {
	         return loanSimulatorService.calculateCreditMentuality(loanAmount, intrestRate, months);

	     }

	     @PostMapping(value = "/carSimulator")
	     @ResponseBody
	     public String carLoan(@RequestParam double loanAmount, double months) {
	         return loanSimulatorService.calculateCarCreditMentuality(loanAmount, months);
	     }

	     @PostMapping(value = "/homeSimulator")
	     @ResponseBody
	     public String homeLoan(@RequestParam double loanAmount, double months) {
	    	 
	      return loanSimulatorService.calculateHomeCreditMentuality(loanAmount, months);
	        
	    	 
	    	
	        	 
	         
	         
	     }
	         
}
