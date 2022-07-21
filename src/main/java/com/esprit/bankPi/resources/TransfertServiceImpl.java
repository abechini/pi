package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

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
	public synchronized TransfertPojo accpetTransfert(TransfertPojo transfert) throws TransactionException {

		Compte compte = StreamSupport.stream(compteRepository.findAll().spliterator(), true)
				.filter(c -> c.getRib().equals(transfert.getNpl())).findFirst()
				.orElseThrow(() -> new TransactionException("Account not found!"));

		if (!compte.isActive()) {
			throw new TransactionException("Account is not active transaction not alloud");
		}

		double realAmount = TransactionUtil.getRealAmount(transfert.getAmount_in_number(), transfert.getCurrency(),
				compte.getCurrency());

		transfert.setCompte(compte);

		compte.setSolde(compte.getSolde() + realAmount);

		compteRepository.save(compte);

		return transfertRepository.save(transfert);
	}

	@Override
	@Transactional
	public synchronized TransfertPojo makeTransfert(TransfertPojo transfert, long idCompte)
			throws TransactionException {

		Compte compte = compteRepository.findById(idCompte)
				.orElseThrow(() -> new TransactionException("Account not found!"));

		if (!compte.isActive()) {
			throw new TransactionException("Account is not active transaction not alloud");
		}

		double realAmount = TransactionUtil.getRealAmount(transfert.getAmount_in_number(), transfert.getCurrency(),
				compte.getCurrency());

		if (!TransactionUtil.hasSuffisantSolde(realAmount, compte.getSolde(), compte.getNegativeCeiling())) {
			throw new TransactionException("Insuffisant solde in the account!");
		}

		transfert.setCompte(compte);

		compte.setSolde(compte.getSolde() - realAmount);

		compteRepository.save(compte);

		return transfertRepository.save(transfert);
	}

	@Override
	public List<TransfertPojo> getByCompte(long idCompte) throws TransactionException {
		return transfertRepository.findByCompte(idCompte);
	}

}
