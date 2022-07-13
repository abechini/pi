package com.esprit.bankPi.enums;

public enum NotificationType {
	 WARNING("warning"),
	    ALERT("alert"),
	    Transaction("transaction"),
	    ACCOUNT("account");

	    private final String typeName;

	    NotificationType(final String typeName) {
	        this.typeName = typeName;
	    }

	    @Override
	    public String toString() {
	        return typeName;
	    }
}
