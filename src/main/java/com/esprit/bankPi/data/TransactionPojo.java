package com.esprit.bankPi.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.esprit.bankPi.enums.CurrencyEnum;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TransactionPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	double amount_in_number;

	String amount;

	@Temporal(TemporalType.DATE)
	Date transaction_date;

	@Enumerated(EnumType.STRING)
	CurrencyEnum currency;
	
	@ManyToOne
	Compte compte;

}
