package com.esprit.bankPi.data;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//JPA Annotations
@Table(name = "data_BankCarte")
@javax.persistence.Entity(name = "data_BankCarte")
public class BankCarte {
	
	private Long carteNumber;
	private Compte compteId;
	private Date expiration;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@javax.persistence.Column(name = "numeroCarte", unique = true, nullable = false, insertable = true, updatable = false)
	public Long getCarteNumber() {
		return carteNumber;
	}
	public void setCarteNumber(Long carteNumber) {
		this.carteNumber = carteNumber;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numeroCompte")
	public Compte getCompteId() {
		return compteId;
	}
	public void setCompteId(Compte compteId) {
		this.compteId = compteId;
	}
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@javax.persistence.Column(name = "expirationDate", unique = false, nullable = false, insertable = true, updatable = true)
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	

}
