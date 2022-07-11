package com.esprit.bankPi.data;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//JPA Annotations
@Table(name = "data_CheckBook")
@javax.persistence.Entity(name = "data_CheckBook")
public class CheckBook {
	
	private Long checkBookNumber;
	private Compte compteId;
	private List<Check> checks;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@javax.persistence.Column(name = "checkBookNumber", unique = true, nullable = false, insertable = true, updatable = false)
	public Long getCheckBookNumber() {
		return checkBookNumber;
	}
	public void setCheckBookNumber(Long checkBookNumber) {
		this.checkBookNumber = checkBookNumber;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = true, mappedBy = "checkBook")
	@javax.persistence.JoinColumn(name = "compteId", unique = false, nullable = true, insertable = true, updatable = false)
	public Compte getCompteId() {
		return compteId;
	}
	public void setCompteId(Compte compteId) {
		this.compteId = compteId;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "checkBookId")
	@javax.persistence.Column(name = "checks", unique = false, nullable = true, insertable = true, updatable = false)
	public List<Check> getChecks() {
		return checks;
	}
	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}
	
	
}
