package com.esprit.bankPi.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esprit.bankPi.data.Compte;

//import org.softib.bank.model.security.Users;
//import org.softib.bank.model.transaction.TransactionCategory;
//import org.softib.bank.model.transaction.TransactionState;
//import org.softib.bank.model.transaction.TransactionType;
import com.esprit.bankPi.repository.*;
//import com.esprit.bankPi.repository.batch.DecisionsBatchRepository;
//import com.esprit.bankPi.service.UserService;
import com.esprit.bankPi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class AccountService {
	 @Autowired
	    private AccountRepository accountRepository;
	 public Compte getAccountById(Long id) {
	        Optional<Compte> act = accountRepository.findById(id);
	        if (act.isPresent()) {
	            return act.get();
	        } else {
	            return null;
	        }
	    }

}
