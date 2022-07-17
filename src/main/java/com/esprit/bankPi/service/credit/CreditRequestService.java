package com.esprit.bankPi.service.credit;

import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.model.credit.CreditRequest;
import com.esprit.bankPi.model.credit.Insurance;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CreditRequestStatus;
import com.esprit.bankPi.enums.CreditStatus;
import com.esprit.bankPi.enums.CreditType;
import com.esprit.bankPi.exception.InvalidArguementException;
import com.esprit.bankPi.repository.credit.CreditRepository;
import com.esprit.bankPi.repository.credit.CreditRequestRepository;
import com.esprit.bankPi.repository.credit.InsuranceRepository;
import com.esprit.bankPi.service.account.AccountService;
import com.esprit.bankPi.util.Constants;
import com.esprit.bankPi.util.SystemMessages;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
@Service
public class CreditRequestService {
	
	 @Autowired
	    private CreditRequestRepository creditRequestRepository;
	 
	 @Autowired
	    private CreditRepository creditRepository;

	    @Autowired
	    private InsuranceRepository insuranceRepository;

	    @Autowired
	    private CreditService creditService;

	    @Autowired
	    private AccountService accountService;
//add

	    public CreditRequest addCreditRequest(CreditRequest creditRequest, Integer id) throws Exception {
	        creditRequest.setCreationDate(new Date());
	        creditRequest.setCreditRequestStatus(CreditRequestStatus.CREATED);
	        creditRequest.setAccount(accountService.getAccountById(id.longValue()));
	        //add insurance
	       insuranceRepository.save(creditRequest.getInsurance());
            
	        creditRequest.setInsurance(creditRequest.getInsurance());
	        
	        return creditRequestRepository.save(creditRequest);
	    }
//delete
	    public String deleteCreditRequest(Integer id) throws Exception {
	        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
	            creditRequestRepository.deleteById(id.longValue());
	            return "Credit Request Deleted Successfully";
	        } else
	            return "Credit Request Not Found";
	    }
//update
	    public String updateCreditRequest(Integer id, CreditRequest newCreditRequest) throws Exception {
	        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
	            CreditRequest oldCreditRequest = creditRequestRepository.findById(id.longValue()).get();
	            if (newCreditRequest.getCin() != null) {
	                oldCreditRequest.setCin(newCreditRequest.getCin());
	            }
	            if (newCreditRequest.getCreditTerm() != null) {
	                oldCreditRequest.setCreditTerm(newCreditRequest.getCreditTerm());
	            }
	            if (newCreditRequest.getCreditAmount() != null) {
	                oldCreditRequest.setCreditAmount(newCreditRequest.getCreditAmount());
	            }
	            if (newCreditRequest.getCreditRepayment() != null) {
	                oldCreditRequest.setCreditRepayment(newCreditRequest.getCreditRepayment());
	            }
	            if (newCreditRequest.getCreditRepaymentAmount() != null) {
	                oldCreditRequest.setCreditRepaymentAmount(newCreditRequest.getCreditRepaymentAmount());
	            }
	            if (newCreditRequest.getAddress() != null) {
	                oldCreditRequest.setAddress(newCreditRequest.getAddress());
	            }
	            if (newCreditRequest.getCivilState() != null) {
	                oldCreditRequest.setCivilState(newCreditRequest.getCivilState());
	            }
	            if (newCreditRequest.getJob() != null) {
	                oldCreditRequest.setJob(newCreditRequest.getJob());
	            }
	            if (newCreditRequest.getAge() != null) {
	                oldCreditRequest.setAge(newCreditRequest.getAge());
	            }
	            if (newCreditRequest.getCreditRequestStatus() != null) {
	                oldCreditRequest.setCreditRequestStatus(newCreditRequest.getCreditRequestStatus());
	            }
	            creditRequestRepository.save(oldCreditRequest);
	            return "Credit Request Updated Successfully";
	        } else {
	            return "Credit Request Not Found";
	        }
	    }
// credit request by id
	    public CreditRequest getCreditRequest(Integer id) throws Exception {
	    	if(creditRequestRepository.findById(id.longValue()).isPresent()) {
	        return creditRequestRepository.findById(id.longValue()).get();}
	    	else 
	    		return null;
	    	
	    	
	    }
	   
    
	   
	  
	    
	    //reject credit
	    public CreditRequest rejectCreditRequest(Integer id) throws Exception {
	        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
	            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
	            if (!creditRequest.getCreditRequestStatus().toString().equals(CreditRequestStatus.CONFIRMED.toString())) {
	                creditRequest.setCreditRequestStatus(CreditRequestStatus.WAITINGFORCLIENTACCEPTANCE);
	                suggestCreditRequest(creditRequest);
	                return creditRequest;
	            }
	        }
	        return null;
	    }
// accept credit
	    public CreditRequest acceptCreditRequestChanges(Integer id) throws Exception {
	        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
	            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
	            if (creditRequest.getCreditRequestStatus().toString().equals(CreditRequestStatus.WAITINGFORCLIENTACCEPTANCE.toString())) {
	                creditRequest.setCreditRequestStatus(CreditRequestStatus.ACCEPTED);
	                creditRequest.setRejectionReason(null);
	                creditRequestRepository.save(creditRequest);
	                return creditRequest;
	            }
	        }
	        return null;
	    }
//add credit
public Credit createCreditFromCreditRequest(Integer id) throws Exception {
	        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
	            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
	            if (creditRequest.getCreditRequestStatus().toString().equals(CreditRequestStatus.VALIDATED.toString())) {
	                creditRequest.setCreditRequestStatus(CreditRequestStatus.CONFIRMED);
	                creditRequestRepository.save(creditRequest);
	               
	                return 
	                		creditRepository.save( mapCreditFromCreditRequest(creditRequest));
	            }
	        }
	        return null;
	    }
//mapping
	    private Credit mapCreditFromCreditRequest(CreditRequest creditRequest) {
	        Credit credit = new Credit();
	        credit.setCreditStatus(CreditStatus.CREATED);
	        credit.setCreditAmount(creditRequest.getCreditAmount());
	        credit.setCreditTerm(creditRequest.getCreditTerm());
	        credit.setCreditRepayment(creditRequest.getCreditRepayment());
	        credit.setCreditRepaymentAmount(creditRequest.getCreditRepaymentAmount());
	        //credit.setType(creditRequest.getType());
	        
	        credit.setCreationDate(LocalDate.now());
	        credit.setCreditInterest(Constants.CREDIT_INTEREST);
	        credit.setCreditRepaymentInterest(Constants.CREDIT_INTEREST);
	        credit.setCreditFees(Constants.CREDIT_FEES);
	        credit.setCreditRequest(creditRequest);
	        credit.setAgent("BankAgent");
	        credit.setReleaseDate(credit.getCreationDate().plusDays(15));
	     
	        credit.setAccount(creditRequest.getAccount());
	        return credit;
	    }

	    public CreditRequest treatCreditRequest(Integer id) {
	        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
	            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
	            creditRequest = checkEligibaleCreditRequest(creditRequest);
	            return creditRequestRepository.save(creditRequest);

	        }
	        return null;
	    }
//request treat
	    public CreditRequest suggestCreditRequest(CreditRequest creditRequest) {

	        String rejectionReason = creditRequest.getRejectionReason();
	        if (rejectionReason.contains(SystemMessages.CREDIT_INSURANCE_NULL)) {
	            Insurance insurance = new Insurance();
	            insurance.setType(creditRequest.getType());
	            insurance.setCreationDate(new Date());
	            insurance.setAmount(creditRequest.getCreditAmount() * Constants.INSURANCE_PERCENTAGE);
	            insurance.setBeneficiary(creditRequest.getAccount().getName());
	            insuranceRepository.save(insurance);
	            creditRequest.setInsurance(insurance);
	        }
	        if (rejectionReason.contains(SystemMessages.CREDIT_CONSUMPTION_CREDIT_TERM_EXCEEDED)) {
	            creditRequest.setCreditTerm(36);
	        }
	        if (rejectionReason.contains(SystemMessages.CREDIT_CAR_CREDIT_TERM_EXCEEDED)) {
	            creditRequest.setCreditTerm(87);
	        }
	        if (rejectionReason.contains(SystemMessages.CREDIT_HOME_CREDIT_TERM_EXCEEDED)) {
	            creditRequest.setCreditTerm(300);
	        }
	        if (rejectionReason.contains(SystemMessages.CREDIT_CONSUMPTION_AMOUNT_EXCEEDED)) {
	            creditRequest.setCreditTerm(20000);
	        }
	        if (rejectionReason.contains(SystemMessages.CREDIT_NOT_ENOUGH_SALARY)) {
	            Double creditAmount = creditRequest.getCreditAmount()
	                    + (creditRequest.getCreditAmount() * Constants.CREDIT_INTEREST)
	                    + creditRequest.getInsurance().getAmount() + Constants.CREDIT_FEES;
	            Double netSalary = creditRequest.getNetSalary();
	            Double amountToPay = 0d;
	            Double possiblePercent = 0d;
	            Integer newCreditTerm = creditRequest.getCreditTerm();
	            boolean check = false;
	            for (int i =creditRequest.getCreditTerm(); i< 36; i++){
	                amountToPay = creditAmount / i;
	                possiblePercent = amountToPay / netSalary;
	                if(possiblePercent <= 0.4){
	                    newCreditTerm = i;
	                    check = true;
	                    break;
	                }
	            }
	            if(check){
	                creditRequest.setCreditTerm(newCreditTerm);
	            }
	            else{
	                creditRequest.setRejectionReason(SystemMessages.CREDIT_NOT_POSSIBLE);
	                creditRequest.setCreditRequestStatus(CreditRequestStatus.CLOSED);
	            }
	        }
	        return creditRequestRepository.save(creditRequest);
	    }


	    private CreditRequest checkEligibaleCreditRequest(CreditRequest creditRequest) {
	        StringBuilder rejectionReason = new StringBuilder();
	        boolean check = true;
	        if (creditRequest.getInsurance() == null) {
	            rejectionReason.append(SystemMessages.CREDIT_INSURANCE_NULL);
	            check = false;
	        }
	        if (calculateAmountToPayForSalary(creditRequest) >= 0.4) {
	            if (rejectionReason.length() > 0) {
	                rejectionReason.append(", ");
	            }
	            rejectionReason.append(SystemMessages.CREDIT_NOT_ENOUGH_SALARY);
	            check = false;
	        }
	      
	        if (creditRequest.getCreditRequestStatus().toString().equals(CreditType.CONSUMPTION.toString())) {
	        	
	            if (creditRequest.getCreditAmount() > 20000) {
	                check = false;
	                if (rejectionReason.length() > 0) {
	                    rejectionReason.append(", ");
	                }
	                rejectionReason.append(SystemMessages.CREDIT_CONSUMPTION_AMOUNT_EXCEEDED);
	            }
	            if (creditRequest.getCreditTerm() > 36) {
	                check = false;
	                if (rejectionReason.length() > 0) {
	                    rejectionReason.append(", ");
	                }
	                rejectionReason.append(SystemMessages.CREDIT_CONSUMPTION_CREDIT_TERM_EXCEEDED);
	            }
	        }
	        if (creditRequest.getCreditRequestStatus().toString().equals(CreditType.CAR.toString())) {
	            if (creditRequest.getCreditTerm() > 87) {
	                check = false;
	                if (rejectionReason.length() > 0) {
	                    rejectionReason.append(", ");
	                }
	                rejectionReason.append(SystemMessages.CREDIT_CAR_CREDIT_TERM_EXCEEDED);
	            }
	        }
	        if (creditRequest.getCreditRequestStatus().toString().equals(CreditType.HOME.toString())) {
	            if (creditRequest.getCreditTerm() > 300) {
	                check = false;
	                if (rejectionReason.length() > 0) {
	                    rejectionReason.append(", ");
	                }
	                rejectionReason.append(SystemMessages.CREDIT_HOME_CREDIT_TERM_EXCEEDED);
	            }
	        }
	        if (check) {
	            creditRequest.setCreditRequestStatus(CreditRequestStatus.VALIDATED);
	            creditRequest.setRejectionReason(null);
	        } else {
	            creditRequest.setCreditRequestStatus(CreditRequestStatus.REJECTED);
	            creditRequest.setRejectionReason(rejectionReason.toString());
	        }
	        return creditRequest;
	    }

	   private Double calculateAmountToPayForSalary(CreditRequest creditRequest) {
	        Integer creditTerm = creditRequest.getCreditTerm();
	        Double creditAmount = 0d;
	        if(creditRequest.getInsurance() != null) {
	            creditAmount = creditRequest.getCreditAmount() + (creditRequest.getCreditAmount() * Constants.CREDIT_INTEREST) + creditRequest.getInsurance().getAmount() + Constants.CREDIT_FEES;
	        } else {
	            creditAmount = creditRequest.getCreditAmount() + (creditRequest.getCreditAmount() * Constants.CREDIT_INTEREST)+ Constants.CREDIT_FEES;

	        }
	        Double netSalary = creditRequest.getNetSalary();
	        Double amountToPay = creditAmount / creditTerm;
	        return amountToPay / netSalary;
	    }
//accepted
	    public Set<CreditRequest> getAllCreditRequestAcceptedFromClients(){
	    	
	        return creditRequestRepository.findAllCreditRequestWithStatus(CreditRequestStatus.ACCEPTED);
	    }
	    
	    //rejected
 public Set<CreditRequest> getAllCreditRequestRejecredFromClients(){
	    	
	        return creditRequestRepository.findAllCreditRequestWithStatus(CreditRequestStatus.REJECTED);
	    }

}
