package com.esprit.bankPi.resources;

import java.util.List;

import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.model.Transfert;

public interface ITransfertService {

	public List<Transfert> getAllTransfert() throws TransactionException;

	public TransfertPojo makeTransfert(double amount, CurrencyEnum currency , String reciver, String sender, String npl) throws TransactionException;

	public List<TransfertPojo> getByCompte(long idCompte) throws TransactionException;

}
