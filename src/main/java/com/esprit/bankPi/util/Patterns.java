package com.esprit.bankPi.util;

public class Patterns {
	  // Common
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'hh:mm";

    // User
    public static final String USER_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
    public static final String USER_ROLE = "USER|ADMIN|ADVISOR|ENTREPRISE|ASSOCIATION";

    //User profile
    public static final String USER_TITLE = "Mr.|Mrs.|Ms.";
    public static final String USER_GENDER = "M|F";
    public static final String USER_CNSS = "^\\d{3}-?\\d{2}-?\\d{4}$";
    public static final String USER_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    public static final String USER_PHONE_REQ = "^[+]?([0-9]*[\\.\\s\\-\\(\\)]|[0-9]+){3,24}$";
    public static final String USER_PHONE_NOT_REQ = "^$|^[+]?([0-9]*[\\.\\s\\-\\(\\)]|[0-9]+){3,24}$";

    // Role
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_UNKNOWN = "UNKNOWN";
    public static final String ROLE_ADVISOR = "ADVISOR";
    public static final String ROLE_ENTREPRISE = "ENTREPRISE";
    public static final String ROLE_ASSOCIATION = "ASSOCIATION";

    //Account
 /*   public static final String ACCOUNT_TRANSFER_ACTION = TransactionType.CREDIT_TRANSACTION + "|" + TransactionType.DEBIT_TRANSACTION;
    public static final String ACCOUNT_TYPE_CODE = Constants.ACCOUNT_CHECKING_CATEGORY + "|" + Constants.ACCOUNT_SAVING_CATEGORY + "|" + Constants.ACCOUNT_INVEST_CATEGORY + "|" + Constants.ACCOUNT_COLLECT_CATEGORY;
    public static final String ACCOUNT_OWNER_TYPE_CODE = Constants.ACCOUNT_OWNER_INDIVIDUAL_CODE + "|" + Constants.ACCOUNT_OWNER_JOINT_CODE + "|" + Constants.ACCOUNT_OWNER_ENTREPRISE_CODE + "|" + Constants.ACCOUNT_OWNER_ASSOCIATION_CODE;
    public static final String ACCOUNT_TRANSACTION_TYPE_CODE = Constants.ACCOUNT_TRANSACTION_TYPE_DEPOSIT_CODE + "|" + Constants.ACCOUNT_TRANSACTION_TYPE_OVERDRAFT_FEE_CODE + "|" + Constants.ACCOUNT_TRANSACTION_TYPE_TRANSFER_CODE;*/
}
