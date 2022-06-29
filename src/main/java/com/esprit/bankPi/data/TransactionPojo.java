package com.esprit.bankPi.data;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.esprit.bankPi.enums.CurrencyEnum;

public abstract class TransactionPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	int amount_in_number;

	String amount;

	@Temporal(TemporalType.DATE)
	Date transaction_date;

	@Enumerated(EnumType.STRING)
	CurrencyEnum currency;

}
