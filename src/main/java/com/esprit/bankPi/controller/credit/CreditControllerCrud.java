package com.esprit.bankPi.controller.credit;
import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.service.credit.CreditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/credits")
public class CreditControllerCrud {

	@Autowired
    CreditService creditService;

    //deleteCredit
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteCredit(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditService.deleteCredit(id), HttpStatus.OK);
    }

    //updateCredit
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateCredit(@PathVariable(value = "id") Integer id, @RequestBody Credit credit) throws Exception {
        return new ResponseEntity<>(creditService.updateCredit(id, credit), HttpStatus.OK);
    }

    //getCredit
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Credit> getCredit(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditService.getCredit(id), HttpStatus.OK);
    }



	
}
