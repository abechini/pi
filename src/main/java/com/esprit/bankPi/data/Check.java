package com.esprit.bankPi.data;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//JPA Annotations
@Table(name = "data_Check")
@javax.persistence.Entity(name = "data_Check")
public class Check {
	
	private Long checkId;
	private CheckBook checkBookId;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@javax.persistence.Column(name = "checkId_", unique = true, nullable = false, insertable = true, updatable = false)
	public Long getCheckId() {
		return checkId;
	}
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkBookNumber_")
	public CheckBook getCheckBookId() {
		return checkBookId;
	}
	public void setCheckBookId(CheckBook checkBookId) {
		this.checkBookId = checkBookId;
	}
	
	
}
