package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.WithdrowRepository;
import com.esprit.bankPi.util.TransactionUtil;

@Service
public class WithdrowServiceImpl implements IWithdrowService {

	@Autowired
	WithdrowRepository withdrawRepository;

	@Autowired
	CompteRepository compteRepository;

	@Override
	@Transactional
	public synchronized WithdrowPojo makeWithdraw(long acountId, double amount) throws TransactionException {
		Compte compte = compteRepository.findById(acountId)
				.orElseThrow(() -> new TransactionException("Acount not found!"));

		if (amount > compte.getSolde() && !TransactionUtil.couldBeInRed(compte)) {
			throw new TransactionException("Insuffisant solde in the account!");
		}

		WithdrowPojo withdraw = new WithdrowPojo();
		withdraw.setAmount_in_number(amount);
		withdraw.setCompte(compte);
		withdraw.setTransaction_date(new Date());

		compte.setSolde(compte.getSolde() - amount);
		compteRepository.save(compte);
		return withdrawRepository.save(withdraw);

	}

	@Override
	public List<WithdrowPojo> getAllWithdraw(long accountId) throws TransactionException {
		return withdrawRepository.findByCompte(accountId);
	}

}
