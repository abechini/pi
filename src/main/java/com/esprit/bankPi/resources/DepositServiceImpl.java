package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.DepositRepository;

@Service
public class DepositServiceImpl implements IDepositService {

	@Autowired
	DepositRepository depositRepository;

	@Autowired
	CompteRepository compteRepository;

	@Transactional
	public synchronized DepositPojo deposit(double amount, CurrencyEnum currency, String rib) throws TransactionException {

		Compte compte = StreamSupport.stream(compteRepository.findAll().spliterator(), false)
				.filter(c -> c.getRib().equals(rib)).findFirst()
				.orElseThrow(() -> new TransactionException("Compte nor found exception!"));
		
		if(!compte.isActive()) {
			throw new TransactionException("Account is not active transaction not alloud");
		}
				
		DepositPojo deposit = new DepositPojo();
		deposit.setAmount_in_number(amount);
		deposit.setCompte(compte);
		deposit.setCurrency(currency);
		deposit.setTransaction_date(new Date());
		deposit.setId(new Random().nextInt());
		
		compte.setSolde(compte.getSolde() + amount);
		compteRepository.save(compte);

		return depositRepository.save(deposit);

	}

	@Override
	public List<DepositPojo> getAllDeposit(long idCompte) throws TransactionException {
		return depositRepository.findByCompte(idCompte);
	}

}
