package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.model.Transfert;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.TransfertRepository;

public class TransfertServiceImpl implements ITransfertService {

	@Autowired
	TransfertRepository transfertRepository;

	@Autowired
	CompteRepository compteRepository;

	@Override
	public List<Transfert> getAllTransfert() throws TransactionException {
		return null;
	}

	@Override
	public void makeTransfert(double amount, CurrencyEnum currency, String reciver, String sender, String npl)
			throws TransactionException {

		Compte compte = new Compte();
//				.findByClientName(reciver)
//				.orElseThrow(() -> new TransactionException("Acount not found!"));

		TransfertPojo transfert = new TransfertPojo();
		transfert.setAmount_in_number(amount);
		transfert.setCurrency(currency);
		transfert.setCompte(compte);
		transfert.setReciver(reciver);
		transfert.setSender(sender);
		transfert.setNpl(npl);
		transfert.setTransaction_date(new Date());

		transfertRepository.save(transfert);

	}

	@Override
	public List<TransfertPojo> getByCompte(long idCompte) throws TransactionException {
		return null;
				
//				transfertRepository.findByCompte(idCompte);
	}

}
