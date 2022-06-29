package com.esprit.bankPi.exceptions;

public class TransactionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TransactionException(String exceptionName) {
		super(exceptionName);
	}
	
}
