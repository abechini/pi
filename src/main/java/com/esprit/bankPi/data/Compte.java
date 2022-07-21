package com.esprit.bankPi.data;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.esprit.bankPi.enums.CompteType;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.util.CompteUtility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//JPA Annotations
@Table(name = "data_Compte")
@javax.persistence.Entity(name = "data_Compte")
public class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long numeroCompte;
	private String rib;
	private Double solde;
	private CurrencyEnum currency;
	private CompteType type;
	@JsonIgnoreProperties("compteId")
	private CheckBook checkBook;
	@JsonIgnoreProperties("compteId")
	private List<BankCarte> bankCartes;
	@JsonIgnoreProperties("compteId")
	private List<Income> incomes;
	@JsonIgnoreProperties("compteList")
	private Client client;
	private boolean isActive;
	private double negativeCeiling;
	private double acountFees;

	@Id
//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@javax.persistence.Column(name = "numeroCompte", unique = true, nullable = false, insertable = true, updatable = false)
	public Long getNumeroCompte() {
		if(numeroCompte!=null && numeroCompte!=0) {
			return numeroCompte;
		}else {
			Random value = new Random();
			String id=String.format("%04d", value.nextInt(100000));
			this.numeroCompte= Long.valueOf(id);
		}
		return numeroCompte;
	}

	public void setNumeroCompte(Long numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	

	@javax.persistence.Column(name = "rib", unique = true, nullable = false, insertable = true, updatable = false)
	public String getRib() {
		if(rib!=null && !rib.isEmpty()) {
			return rib;
		}else {
			setRib(CompteUtility.accountNumber(this));
		}
		return rib;
	}

	private void setRib(String rib) {
		this.rib = rib;
	}
	
	@javax.persistence.Column(name = "solde", unique = false, nullable = false, insertable = true, updatable = true)
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}
	
	@javax.persistence.Column(name = "currency", unique = false, nullable = false, insertable = true, updatable = true)
	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}
	
	@javax.persistence.Column(name = "type", unique = false, nullable = false, insertable = true, updatable = false)
	public CompteType getType() {
		return type;
	}

	public void setType(CompteType type) {
		this.type = type;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
	@javax.persistence.JoinColumn(name = "checkBook", unique = false, nullable = true, insertable = true, updatable = true)
	public CheckBook getCheckBook() {
		return checkBook;
	}

	public void setCheckBook(CheckBook checkBook) {
		this.checkBook = checkBook;
	}
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	public List<BankCarte> getBankCartes() {
		return bankCartes;
	}

	public void setBankCartes(List<BankCarte> bankCartes) {
		this.bankCartes = bankCartes;
	}

	@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
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
	
	@javax.persistence.Column(name = "isActive", unique = false, nullable = true, insertable = true, updatable = true)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@javax.persistence.Column(name = "negativeCeiling", unique = false, nullable = true, insertable = true, updatable = true)
	public double getNegativeCeiling() {
		return negativeCeiling;
	}

	public void setNegativeCeiling(double negativeCeiling) {
		this.negativeCeiling = negativeCeiling;
	}
	
	@javax.persistence.Column(name = "accountFees", unique = false, nullable = true, insertable = true, updatable = true)
	public double getAcountFees() {
		return acountFees;
	}

	public void setAcountFees(double acountFees) {
		this.acountFees = acountFees;
	}
}
