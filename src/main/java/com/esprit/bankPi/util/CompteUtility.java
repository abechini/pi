package com.esprit.bankPi.util;

import java.util.Random;

import com.esprit.bankPi.data.Compte;

public class CompteUtility {
	private static String COUNTRY = "TN";
	private static String BANK_ID = "1234";
	private static String UNKNOWN_AGENCY="9999";

	public static String accountNumber(Compte compte) {
		String id = COUNTRY;
		Random value = new Random();
		int r1 = value.nextInt(10);
		int r2 = value.nextInt(10);
		id += Integer.toString(r1) + Integer.toString(r2) + "-";
		id +=BANK_ID;
		id+="-";
		if(compte.getClient()!=null&&compte.getClient().getAgency()!=null) {
			id += compte.getClient().getAgency().getId();
		}else {
			id += UNKNOWN_AGENCY;
		}
		 id+="-";
		 id+=compte.getNumeroCompte();
		return id;
	}
	public static String getBankID(String number) {
		return BANK_ID;
	}
	public static String getAgencyID(String number) {
		return number.substring(number.indexOf(getBankID(number))+2, number.lastIndexOf("-"));
	}
	public static String getCompteID(String number) {
		return number.substring(number.lastIndexOf("-")+1);
	}
}
