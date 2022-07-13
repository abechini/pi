package com.esprit.bankPi.service.credit;

import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.model.credit.CreditRequest;
import com.esprit.bankPi.model.credit.Payment;
import com.esprit.bankPi.enums.CreditRequestStatus;
import com.esprit.bankPi.enums.CreditStatus;
import com.esprit.bankPi.repository.credit.CreditRepository;
import com.esprit.bankPi.repository.credit.CreditRequestRepository;
import com.esprit.bankPi.repository.credit.PaymentRepository;
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

	    @Autowired
	    private PaymentRepository paymentRepository;


	    public Credit addCredit(Credit credit) throws Exception {

	                 if (credit.getCreditRequest().getCreditRequestStatus().toString().equals(CreditRequestStatus.CONFIRMED.toString())) {
	                     Set<Payment> payments = new HashSet<>();
	                     Double totalCreditAmount = credit.getCreditAmount() + (credit.getCreditAmount() * Constants.CREDIT_INTEREST) + Constants.CREDIT_FEES + credit.getCreditRequest().getInsurance().getAmount();
	                             Payment payment = null;
	                     LocalDate date = LocalDate.now();
	                     LocalDate firstDayOfMonth = LocalDate.of(date.getYear(),date.getMonth(),1);
	                     for(int i = 0; i< credit.getCreditTerm(); i++){
	                         firstDayOfMonth = firstDayOfMonth.plusMonths(1);
	                         payment = new Payment();
	                         payment.setCreationDate(date);
	                         payment.setPaymentStatus(CreditStatus.CREATED);
	                         payment.setPaymentAmount(totalCreditAmount / credit.getCreditTerm());
	                         payment.setPaymentInterest(credit.getCreditInterest());
	                         payment.setPaymentDueDate(firstDayOfMonth);
	                         payment.setPaymentInterest(Constants.CREDIT_INTEREST);
	                         payments.add(payment);
	                     }
	                     credit.setPayments(payments);
	                     credit.setPayedAmount(0d);
	                     credit.setRemainingAmount(totalCreditAmount);
	                     creditRepository.save(credit);
	                     for (Payment payment1 : payments){
	                         payment1.setCredit(credit);
	                     }
	                     paymentRepository.saveAll(payments);
	                     return creditRepository.findById(credit.getId()).get();
	                 }
	                 return null;
	    }

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
	            if (newCredit.getCreditRepayment() != null) {
	                oldCredit.setCreditRepayment(newCredit.getCreditRepayment());
	            }
	            if (newCredit.getCreditRepaymentAmount() != null) {
	                oldCredit.setCreditRepaymentAmount(newCredit.getCreditRepaymentAmount());
	            }
	            if (newCredit.getCreditFees() != null) {
	                oldCredit.setCreditFees(newCredit.getCreditFees());
	            }
	            if (newCredit.getCreditInterest() != null) {
	                oldCredit.setCreditInterest(newCredit.getCreditInterest());
	            }
	            if (newCredit.getCreditRepaymentInterest() != null) {
	                oldCredit.setCreditRepaymentInterest(newCredit.getCreditRepaymentInterest());
	            }
	            if (newCredit.getPayedAmount() != null) {
	                oldCredit.setPayedAmount(newCredit.getPayedAmount());
	            }
	            if (newCredit.getRemainingAmount() != null) {
	                oldCredit.setRemainingAmount(newCredit.getRemainingAmount());
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
