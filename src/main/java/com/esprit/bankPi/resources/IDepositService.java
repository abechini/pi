package com.esprit.bankPi.resources;

import java.util.List;

import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;

public interface IDepositService {

	DepositPojo deposit(double amount, CurrencyEnum currency, String rib) throws TransactionException;

	List<DepositPojo> getAllDeposit(long idCompte) throws TransactionException;

}
