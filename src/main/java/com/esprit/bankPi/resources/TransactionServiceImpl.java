package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.data.TransactionPojo;
import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.repository.DepositRepository;
import com.esprit.bankPi.repository.TransfertRepository;
import com.esprit.bankPi.util.ExcelHelper;

@Service
public class TransactionServiceImpl implements ITransactionService {
	@Autowired
	TransfertRepository transfertRepository;

	@Autowired
	DepositRepository depositRepository;

	@Override
	public ByteArrayInputStream exportExtrait(long idCompte) {
		List<TransactionPojo> transactions = new ArrayList<>();
		List<TransfertPojo> transferts = transfertRepository.findByCompte(idCompte);
		transactions.addAll(transferts);
		List<DepositPojo> deposits = depositRepository.findByCompte(idCompte);
		transactions.addAll(deposits);
		return ExcelHelper.extrait(transactions);

	}

}
