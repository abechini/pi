package com.esprit.bankPi.simulator;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


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
