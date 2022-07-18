package com.esprit.bankPi.util;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CompteType;

public class TransactionUtil {
	
	public static boolean couldBeInRed( Compte c ) {
		CompteType type = c.getType();
		if ( type.equals(CompteType.DEPOSIT) ) {
			return true;
		}
		return false;
	}

}
