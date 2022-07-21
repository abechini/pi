package com.esprit.bankPi.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.esprit.bankPi.enums.CurrencyEnum;

@Entity
public class TransfertPojo extends TransactionPojo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String sender;

	String reciver;

	String description;

	// rib
	String npl;

	CurrencyEnum currency;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNpl() {
		return npl;
	}

	public void setNpl(String npl) {
		this.npl = npl;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}
}
