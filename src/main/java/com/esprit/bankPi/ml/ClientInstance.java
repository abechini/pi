package com.esprit.bankPi.ml;

import com.esprit.bankPi.enums.CivilState;
import com.esprit.bankPi.enums.Sexe;

public class ClientInstance {
	private int month;
	private Sexe sexe;
	private int ageRange;
	private CivilState civilState;
	private Double savings;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public int getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}
	public CivilState getCivilState() {
		return civilState;
	}
	public void setCivilState(CivilState civilState) {
		this.civilState = civilState;
	}
	public Double getSavings() {
		return savings;
	}
	public void setSavings(Double savings) {
		this.savings = savings;
	}
	@Override
	public String toString() {
		return "{mois:" + month + ", sexe:" + sexe + ", ageRange:" + ageRange + ", civilState:"
				+ civilState + ", savings:" + savings==null?"?":savings + "}";
	}
	
	
}
