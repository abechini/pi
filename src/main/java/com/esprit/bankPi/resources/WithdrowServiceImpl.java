package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.WithdrowRepository;

@Service
public class WithdrowServiceImpl implements IWithdrowService {

	@Autowired
	WithdrowRepository withdrawRepository;

	@Autowired
	CompteRepository compteRepository;

	@Override
	public void makeWithdraw(long acountId, double amount) throws TransactionException {
		Compte compte = compteRepository.findById(acountId)
				.orElseThrow(() -> new TransactionException("Acount not found!"));
		WithdrowPojo withdraw = new WithdrowPojo();
		withdraw.setAmount_in_number(amount);
		withdraw.setCompte(compte);
		withdraw.setTransaction_date(new Date());

		withdrawRepository.save(withdraw);

	}

	@Override
	public List<WithdrowPojo> getAllWithdraw(long accountId) throws TransactionException {
//		return withdrawRepository.findByCompte(accountId);
		return null;
	}

}
