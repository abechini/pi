package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.model.Deposit;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.DepositRepository;

@Service
public class DepositServiceImpl implements IDepositService {

	@Autowired
	DepositRepository depositRepository;

	@Autowired
	CompteRepository compteRepository;

	public void deposit(double amount, CurrencyEnum currency, long idCompte) throws TransactionException {

		Compte compte = compteRepository.findById(idCompte)
				.orElseThrow(() -> new TransactionException("Compte not found"));

		DepositPojo deposit = new DepositPojo();
		deposit.setAmount_in_number(amount);
		deposit.setCompte(compte);
		deposit.setCurrency(currency);
		deposit.setTransaction_date(new Date());
		deposit.setId(new Random().nextInt());

		depositRepository.save(deposit);

	}

	@Override
	public List<DepositPojo> getAllDeposit(long idCompte) throws TransactionException {
		return null;
//		return depositRepository.findByCompte(idCompte);
		
	}

}
