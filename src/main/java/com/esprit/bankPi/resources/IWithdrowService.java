package com.esprit.bankPi.resources;

import java.util.List;

import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.exceptions.TransactionException;

public interface IWithdrowService {

	WithdrowPojo makeWithdraw(long acountId, double amount) throws TransactionException;

	List<WithdrowPojo> getAllWithdraw(long accountId) throws TransactionException;

}
