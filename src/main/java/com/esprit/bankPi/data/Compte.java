package com.esprit.bankPi.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.esprit.bankPi.enums.CompteType;
import com.esprit.bankPi.enums.Currency;

//JPA Annotations
@Table(name = "data_Compte")
@javax.persistence.Entity(name = "data_Compte")
public class Compte {

	private Long numeroCompte;
	private Float solde;
	private Currency currency;
	private CompteType type;
	private CheckBook checkBook;
	private List<BankCarte> bankCartes;
	private List<Income> incomes;
	private Client client;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@javax.persistence.Column(name = "numeroCompte", unique = true, nullable = false, insertable = true, updatable = false)
	public Long getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(Long numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	
	@javax.persistence.Column(name = "solde", unique = false, nullable = false, insertable = true, updatable = true)
	public Float getSolde() {
		return solde;
	}
	public void setSolde(Float solde) {
		this.solde = solde;
	}
	
	@javax.persistence.Column(name = "currency", unique = false, nullable = false, insertable = true, updatable = true)
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	@javax.persistence.Column(name = "type", unique = false, nullable = false, insertable = true, updatable = false)
	public CompteType getType() {
		return type;
	}

	public void setType(CompteType type) {
		this.type = type;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@javax.persistence.JoinColumn(name = "checkBook", unique = false, nullable = true, insertable = true, updatable = true)
	public CheckBook getCheckBook() {
		return checkBook;
	}

	public void setCheckBook(CheckBook checkBook) {
		this.checkBook = checkBook;
	}
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	//(fetch = FetchType.LAZY)
	//@JoinColumn(name = "bankCartes")
	//@javax.persistence.Column(name = "bankCartes", unique = false, nullable = true, insertable = true, updatable = true)
	public List<BankCarte> getBankCartes() {
		return bankCartes;
	}

	public void setBankCartes(List<BankCarte> bankCartes) {
		this.bankCartes = bankCartes;
	}
	
	@OneToMany(cascade = CascadeType.REMOVE , orphanRemoval = true)
	//(fetch = FetchType.LAZY, mappedBy = "compteId")
	//@javax.persistence.Column(name = "incomes", unique = false, nullable = true, insertable = true, updatable = true)
	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
