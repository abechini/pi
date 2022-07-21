package com.esprit.bankPi.data;

import java.text.SimpleDateFormat;
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

import com.esprit.bankPi.enums.CurrencyEnum;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TransactionPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	double amount_in_number;

	String amount;

	String transaction_date ;

	@Enumerated(EnumType.STRING)
	CurrencyEnum currency;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount_in_number() {
		return amount_in_number;
	}

	public void setAmount_in_number(double amount_in_number) {
		this.amount_in_number = amount_in_number;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@ManyToOne
	Compte compte;
	
	public void setTransaction_date(Date date) {
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		this.transaction_date = DateFor.format(date);
	}

}
