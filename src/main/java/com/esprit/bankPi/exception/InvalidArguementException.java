package com.esprit.bankPi.exception;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;


import com.esprit.bankPi.simulator.LoanSimulatorService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ResponseStatus(HttpStatus.OK)


public class InvalidArguementException extends RuntimeException{
	 private static final Logger LOG = LoggerFactory.getLogger(LoanSimulatorService.class);
	private static final long serialVersionUID = 4113204222861006995L;
	
	
	 public InvalidArguementException() {
	     /*   super();
	        LOG.error("amel");*/
	    }
	
	
	    public InvalidArguementException(String message) {
	    	
	  	super(message);
	    	LOG.error(message);  
	    }
	
	
	

}
