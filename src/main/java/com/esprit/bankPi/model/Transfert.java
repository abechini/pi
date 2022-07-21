package com.esprit.bankPi.model;

import com.esprit.bankPi.enums.CurrencyEnum;

import lombok.Builder;
import lombok.Data;

@Data
public class Transfert extends Transaction {

	String sender;

	String reciver;

	String description;

	// rib
	String npl;

	CurrencyEnum currency;
}
