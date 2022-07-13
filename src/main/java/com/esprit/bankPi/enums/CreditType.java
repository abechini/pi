package com.esprit.bankPi.enums;

public enum CreditType {
    CAR("car"),
    HOME("home"),
    CONSUMPTION("consumption");

	    private final String typeName;

	    CreditType(final String typeName) {
	        this.typeName = typeName;
	    }

}
