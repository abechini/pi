package com.esprit.bankPi.service.account;



import com.esprit.bankPi.data.Compte;


import com.esprit.bankPi.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
