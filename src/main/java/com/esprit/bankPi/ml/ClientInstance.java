package com.esprit.bankPi.ml;

import java.util.Collections;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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
//		return "{mois:" + month + ", sexe:" + sexe + ", ageRange:" + ageRange + ", civilState:"
//				+ civilState + ", savings:" + (savings==null?"?":savings )+ "}";
		return "["+ month + "," + (sexe==Sexe.Male?1:0) + "," + ageRange + ","+ (civilState==CivilState.Married?0:1) + "," + (savings==null?"?":savings )+"]";
	}
	
	public Object[] toArray() {
		return new Object[]{month,sexe,ageRange,civilState,(savings==null?"?":savings )};
	}
	public JSONArray toJsonArray() throws JSONException {
		return new JSONArray(toString());
	}
	public JSONObject toJsonObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("sparse", false);
		object.put("weight", Collections.EMPTY_LIST);
		object.put("values", toJsonArray());
		return object;
		
	}
	
	public ClientInstance clone() {
		ClientInstance inst = new ClientInstance();
		inst.setAgeRange(ageRange);
		inst.setCivilState(civilState);
		inst.setSavings(savings);
		inst.setSexe(sexe);
		inst.setMonth(month);
		return inst;
	}
	
}
