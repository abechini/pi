package com.esprit.bankPi.service.credit;

import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.model.credit.CreditRequest;

import com.esprit.bankPi.enums.CreditRequestStatus;
import com.esprit.bankPi.enums.CreditStatus;
import com.esprit.bankPi.repository.credit.CreditRepository;
import com.esprit.bankPi.repository.credit.CreditRequestRepository;

import com.esprit.bankPi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class CreditService {
	
	 @Autowired
	    private CreditRepository creditRepository;

	    public String deleteCredit(Integer id) throws Exception {
	        if (creditRepository.findById(id.longValue()).isPresent()) {
	            creditRepository.deleteById(id.longValue());
	            return "Credit Deleted Successfully";
	        } else
	            return "Credit Not Found";
	    }

	    public String updateCredit(Integer id, Credit newCredit) throws Exception {
	        if (creditRepository.findById(id.longValue()).isPresent()) {
	            Credit oldCredit = creditRepository.findById(id.longValue()).get();
	            if (newCredit.getAgent() != null) {
	                oldCredit.setAgent(newCredit.getAgent());
	            }
	            if (newCredit.getCreditTerm() != null) {
	                oldCredit.setCreditTerm(newCredit.getCreditTerm());
	            }
	            if (newCredit.getCreditAmount() != null) {
	                oldCredit.setCreditAmount(newCredit.getCreditAmount());
	            }

	            if (newCredit.getCreditFees() != null) {
	                oldCredit.setCreditFees(newCredit.getCreditFees());
	            }
	            if (newCredit.getCreditInterest() != null) {
	                oldCredit.setCreditInterest(newCredit.getCreditInterest());
	            }
	            if (newCredit.getReleaseDate() != null) {
	                oldCredit.setReleaseDate(newCredit.getReleaseDate());
	            }
	            if (newCredit.getType() != null) {
	                oldCredit.setType(newCredit.getType());
	            }
	            creditRepository.save(oldCredit);
	            return "Credit Updated Successfully";
	        } else {
	            return "Credit Not Found";
	        }
	    }

	    public Credit getCredit(Integer id) throws Exception {
	        return creditRepository.findById(id.longValue()).get();
	    }


}
