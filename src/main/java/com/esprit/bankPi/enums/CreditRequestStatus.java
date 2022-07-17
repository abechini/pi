package com.esprit.bankPi.enums;

public enum CreditRequestStatus {
	  CREATED("created"),
	    VALIDATED("validated"),
	    CONFIRMED("confirmed"),
	    REJECTED("rejected"),
	    ACCEPTED("accepted"),
	    WAITINGFORCLIENTACCEPTANCE("waiting for client acceptance"),
	    CLOSED("closed");
	    private final String typeName;

	    CreditRequestStatus(final String typeName) {
	        this.typeName = typeName;
	    }

	    @Override
	    public String toString() {
	        return typeName;
	    }
}
