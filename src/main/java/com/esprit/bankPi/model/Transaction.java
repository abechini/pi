package com.esprit.bankPi.model;

import java.util.Date;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CurrencyEnum;

import lombok.Data;

@Data
public class Transaction {

	int id;

	double amount_in_number;

	String amount;

	Date transaction_date;

	CurrencyEnum currency;

	Compte compte;

}
