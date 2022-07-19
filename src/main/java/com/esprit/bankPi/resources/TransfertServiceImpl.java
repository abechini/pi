package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.model.Transfert;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.TransfertRepository;
import com.esprit.bankPi.util.TransactionUtil;

@Service
public class TransfertServiceImpl implements ITransfertService {

	@Autowired
	TransfertRepository transfertRepository;

	@Autowired
	CompteRepository compteRepository;

	@Autowired
	IWithdrowService withdrowService;

	@Autowired
	IDepositService depositService;

	@Override
	public List<Transfert> getAllTransfert() throws TransactionException {
		return null;
	}

	@Override
	@Transactional
	public synchronized TransfertPojo accpetTransfert(double amount, CurrencyEnum currency, String reciver,
			String sender, String nplReciever) throws TransactionException {

		Compte compte = compteRepository.findByClientName(reciver)
				.orElseThrow(() -> new TransactionException("Acount not found!"));

		if (!compte.isActive()) {
			throw new TransactionException("Account is not active transaction not alloud");
		}

		TransfertPojo transfert = new TransfertPojo();

		transfert.setAmount_in_number(TransactionUtil.getRealAmount(amount, currency, compte.getCurrency()));

		transfert.setCurrency(currency);
		transfert.setCompte(compte);
		transfert.setReciver(reciver);
		transfert.setSender(sender);
		transfert.setNpl(nplReciever);
		transfert.setTransaction_date(new Date());

		depositService.deposit(amount, currency, nplReciever);

		return transfertRepository.save(transfert);
	}

	@Override
	@Transactional
	public synchronized TransfertPojo makeTransfert(double amount, CurrencyEnum currency, String reciver, String sender,
			String nplReciever) throws TransactionException {

		Compte compte = compteRepository.findByClientName(reciver)
				.orElseThrow(() -> new TransactionException("Account not found!"));

		if (!compte.isActive()) {
			throw new TransactionException("Account is not active transaction not alloud");
		}

		TransfertPojo transfert = new TransfertPojo();

		transfert.setAmount_in_number(TransactionUtil.getRealAmount(amount, currency, compte.getCurrency()));

		transfert.setCurrency(currency);
		transfert.setCompte(compte);
		transfert.setReciver(reciver);
		transfert.setSender(sender);
		transfert.setNpl(nplReciever);
		transfert.setTransaction_date(new Date());

		withdrowService.makeWithdraw(compte.getRib(), amount);

		return transfertRepository.save(transfert);
	}

	@Transactional
	public synchronized TransfertPojo makeTransfert1(TransfertPojo transfert) throws TransactionException {

		Compte compte = compteRepository.findByClientName(transfert.getReciver())
				.orElseThrow(() -> new TransactionException("Account not found!"));

		if (!compte.isActive()) {
			throw new TransactionException("Account is not active transaction not alloud");
		}

		withdrowService.makeWithdraw(compte.getRib(), TransactionUtil.getRealAmount(transfert.getAmount_in_number(),
				transfert.getCurrency(), compte.getCurrency()));

		return transfertRepository.save(transfert);
	}

	@Override
	public List<TransfertPojo> getByCompte(long idCompte) throws TransactionException {
		return transfertRepository.findByCompte(idCompte);
	}

}
