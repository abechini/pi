package com.esprit.bankPi.resources;

import java.util.List;

import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.model.Transfert;

public interface ITransfertService {

	public List<Transfert> getAllTransfert() throws TransactionException;

	public TransfertPojo accpetTransfert(TransfertPojo transfert) throws TransactionException;

	public List<TransfertPojo> getByCompte(long idCompte) throws TransactionException;

	public TransfertPojo makeTransfert(TransfertPojo transfert, long idCompte) throws TransactionException;

}
