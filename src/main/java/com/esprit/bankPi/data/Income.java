package com.esprit.bankPi.data;

import java.time.YearMonth;

import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.esprit.bankPi.enums.IncomeType;
import com.esprit.bankPi.util.YearMonthDateAttributeConverter;

//JPA Annotations
@Table(name = "data_income")
@javax.persistence.Entity(name = "data_income")
public class Income {
	
	Long incomeID;
	IncomeType incomeType;
	Double incomeAmount;
	YearMonth incomeEndDate;
	Compte compteId;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@javax.persistence.Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
	public Long getIncomeID() {
		return incomeID;
	}
	public void setIncomeID(Long incomeID) {
		this.incomeID = incomeID;
	}
	@javax.persistence.Column(name = "incomeType", unique = false, nullable = false, insertable = true, updatable = true)
	public IncomeType getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}
	@javax.persistence.Column(name = "amount", unique = false, nullable = false, insertable = true, updatable = true)
	public Double getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(Double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	@Convert(converter = YearMonthDateAttributeConverter.class)
	@javax.persistence.Column(name = "incomeEndDate", unique = false, nullable = false, insertable = true, updatable = true)	
	public YearMonth getIncomeEndDate() {
		return incomeEndDate;
	}
	public void setIncomeEndDate(YearMonth incomeEndDate) {
		this.incomeEndDate = incomeEndDate;
	}
	
	@ManyToOne
	//(fetch = FetchType.LAZY)
    //@JoinColumn(name = "numeroCompte")
	public Compte getCompteId() {
		return compteId;
	}
	public void setCompteId(Compte compteId) {
		this.compteId = compteId;
	}
	
	
}
