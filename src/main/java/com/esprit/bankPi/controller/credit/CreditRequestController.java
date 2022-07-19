package com.esprit.bankPi.controller.credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.model.credit.CreditRequest;
import com.esprit.bankPi.service.credit.CreditRequestService;


import java.util.Set;

@RestController
@RequestMapping(value = "/api/creditrequests")
public class CreditRequestController {
	
	 @Autowired
	    CreditRequestService creditRequestService;

	   
//add Resquest
	
	 
	 @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<String> createCreditRequest(@RequestBody CreditRequest creditRequest, @PathVariable(value = "id") Integer id) throws Exception {
	        return new ResponseEntity<>(creditRequestService.addCreditRequest(creditRequest,id), HttpStatus.OK);
	    }
//deleteCreditRequest
	    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<String> deleteCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
	        return new ResponseEntity<>(creditRequestService.deleteCreditRequest(id), HttpStatus.OK);
	    }

	    //updateCreditRequest
	    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<String> updateCreditRequest(@PathVariable(value = "id") Integer id, @RequestBody CreditRequest creditRequest) throws Exception {
	        return new ResponseEntity<>(creditRequestService.updateCreditRequest(id, creditRequest), HttpStatus.OK);
	    }
//getCreditRequestby id
	    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<CreditRequest> getCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
	        return new ResponseEntity<>(creditRequestService.getCreditRequest(id), HttpStatus.OK);
	         
	    }
	
//createCreditFromCreditRequest
	    @PostMapping(value = "/{id}/create-credit", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Credit> acceptCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
	        return new ResponseEntity<>(creditRequestService.createCreditFromCreditRequest(id), HttpStatus.OK);
	    }
	    
	 
//rejectCreditRequest   suggestCreditRequest
	    @PutMapping(value = "/{id}/suggest", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<CreditRequest> rejectCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
	        return new ResponseEntity<>(creditRequestService.rejectCreditRequest(id), HttpStatus.OK);
	    }
	    
	    
//acceptCreditRequestChanges
	    @PutMapping(value = "/{id}/client-accept", produces = MediaType.APPLICATION_JSON_VALUE)
	  //  @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<CreditRequest> acceptCreditRequestChanges(@PathVariable(value = "id") Integer id) throws Exception {
	        return new ResponseEntity<>(creditRequestService.acceptCreditRequestChanges(id), HttpStatus.OK);
	    }
//treatCreditRequest
	    @PutMapping(value = "/{id}/treat", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<CreditRequest> treatCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
	        return new ResponseEntity<>(creditRequestService.treatCreditRequest(id), HttpStatus.OK);
	    }
	    
	    
//getAllCreditRequestAcceptedFromClients()
	    @GetMapping(value = "/client-accepted", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Set<CreditRequest>> getAllCreditRequestAcceptedFromClients() throws Exception {
	        return new ResponseEntity<>(creditRequestService.getAllCreditRequestAcceptedFromClients(), HttpStatus.OK);
	    }
	    
	  //getAllCreditRequestAcceptedFromClients()
	    @GetMapping(value = "/client-rejected", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Set<CreditRequest>> getAllCreditRequestRejecteFromClients() throws Exception {
	        return new ResponseEntity<>(creditRequestService.getAllCreditRequestRejecredFromClients(), HttpStatus.OK);
	    }
	  
}
