package com.esprit.bankPi.resources;

import java.util.List;

import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.model.Deposit;

public interface IDepositService {

	void deposit(double amount, CurrencyEnum currency, long idCompte) throws TransactionException;

	List<DepositPojo> getAllDeposit(long idCompte) throws TransactionException;

}
