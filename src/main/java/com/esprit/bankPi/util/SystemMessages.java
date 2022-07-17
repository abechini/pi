package com.esprit.bankPi.util;

public class SystemMessages {
	  //User
    public static final String USER_PASSWORD_REQUIRED = "Password is required.";
    public static final String USER_PASSWORD_FORMAT = "Password must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters";
    public static final String USER_CURRENT_PASSWORD_REQUIRED = "Current Password is required.";
    public static final String USER_NEW_PASSWORD_REQUIRED = "New Password is required.";
    public static final String USER_SAME_PASSWORD = "New Password is the same as the current password. Please enter a new Password.";
    public static final String USER_PASSWORD_NOT_MATCH = "Current Password does not match the current password. Please enter the correct current password.";
    public static final String USER_ROLE_FORMAT = "Role must be 'USER', 'ADVISOR', 'ENTREPRISE', 'ASSOCIATION' or 'ADMIN'";
    public static final String USER_DELETE_SELF = "Unable to delete your own account.";

    //User profile
    public static final String USER_FIRST_NAME_REQUIRED = "First Name is required.";
    public static final String USER_LAST_NAME_REQUIRED = "Last Name is required.";
    public static final String USER_TITLE_REQUIRED = "Title is required.";
    public static final String USER_TITLE_FORMAT = "Title must be 'Mr.', 'Mrs.', or 'Ms.'.";
    public static final String USER_GENDER_REQUIRED = "Gender is required.";
    public static final String USER_GENDER_FORMAT = "Gender must be 'M' or 'F'.";
    public static final String USER_CNSS_FORMAT = "National Social Security Number must be in a valid format '###-##-####' ";
    public static final String USER_CNSS_REQUIRED = "National Social Security Number is required.";
    public static final String USER_DOB_REQUIRED = "Date of Birth is required.";
    public static final String USER_EMAIL_REQUIRED = "Email Address is required.";
    public static final String USER_EMAIL_FORMAT = "Email Address must be a valid email format.";
    public static final String USER_PHONE_HOME_FORMAT = "Home Phone Number must be a valid phone number format '(+###) ##-###-###' ";
    public static final String USER_PHONE_HOME_REQUIRED = "Home Phone is required.";
    public static final String USER_PHONE_MOBILE_FORMAT = "Mobile Phone Number must be a valid phone number format '(+###) ##-###-###' ";
    public static final String USER_PHONE_WORK_FORMAT = "Work Phone Number must be a valid phone number format '(+###) ##-###-###' ";
    public static final String USER_ADDRESS_REQUIRED = "Address is required.";
    public static final String USER_LOCALITY_REQUIRED = "Locality is required.";
    public static final String USER_REGION_REQUIRED = "Region is required.";
    public static final String USER_POSTAL_CODE_REQUIRED = "Postal Code is required.";
    public static final String USER_COUNTRY_REQUIRED = "Country is required.";
    public static final String USER_EMAIL_EXISTS = "An account is already registered with the email address provided.";
    public static final String USER_CNSS_EXISTS = "An account is already registered with the National Social Security Number provided.";

    // API Authentication
    public static final String API_INVALID_TOKEN = "Expired or Invalid Json Web Token";
    public static final String API_INVALID_CRED = "Invalid Credentials, Authentication Failed";

    // Common
    public static final String INVALID_OBJECT_ID = "Invalid request format. Object Id should be a positive integer.";
    public static final String OBJECT_NOT_FOUND = "Object Not Found. Unable to locate object with id ";
    //public static final String ACCESS_FORBIDDEN = "You must have an '" + Patterns.ROLE_ADMIN + "' role to access data outside the scope of the current authenticated user.";

    //Account
    public static final String ACCOUNT_NAME_REQUIRED = "Account Name is required.";
    public static final String ACCOUNT_INIT_DEPOSIT_REQUIRED = "Initial Deposit is required.";
    public static final String ACCOUNT_TRANSACTION_POSITIVE = "Transaction amount must be a positive number.";
    public static final String ACCOUNT_TRANSACTION_DESCRIPTION_REQUIRED = "Transaction Description is required.";
    public static final String ACCOUNT_TRANSACTION_TYPE_CODE_REQUIRED = "Transaction Type Code is required.";
    public static final String ACCOUNT_TYPE_REQUIRED = "Account Type Code is required.";
    public static final String ACCOUNT_OWNER_TYPE_REQUIRED = "Ownership Type Code is required.";
   // public static final String ACCOUNT_TYPE_FORMAT = "Account Type Code must be '" + Constants.ACCOUNT_CHECKING_CATEGORY + "' or '" + Constants.ACCOUNT_SAVING_CATEGORY + "' or '" + Constants.ACCOUNT_INVEST_CATEGORY + "' or '" + Constants.ACCOUNT_COLLECT_CATEGORY + "'.";
   // public static final String ACCOUNT_OWN_TYPE_FORMAT = "Ownership Type Code must be '" + Constants.ACCOUNT_OWNER_INDIVIDUAL_CODE + "' or '" + Constants.ACCOUNT_OWNER_JOINT_CODE + "' or '" + Constants.ACCOUNT_OWNER_ENTREPRISE_CODE + "' or '" + Constants.ACCOUNT_OWNER_ASSOCIATION_CODE + "'.";
   // public static final String ACCOUNT_TRANSFER_ACTION = "Transaction Action must be '" + TransactionType.CREDIT_TRANSACTION + "' or '" + TransactionType.DEBIT_TRANSACTION + "' and it's required";
  //  public static final String ACCOUNT_TRAN_TYPE_FORMAT = "Transaction Type must be one of " + Constants.ACCOUNT_TRANSACTION_TYPE_DEPOSIT_CODE + ", " + Constants.ACCOUNT_TRANSACTION_TYPE_OVERDRAFT_FEE_CODE + ", " + Constants.ACCOUNT_TRANSACTION_TYPE_TRANSFER_CODE;


    //Promotions
    public static final String PROMO_AGE_FORMAT = "";

    //Credit
    public static final String CREDIT_NOT_POSSIBLE= "IMPOSSIBLE CREDIT APPLICATION";
    public static final String CREDIT_INSURANCE_NULL= "INSURANCE IS MISSING";
    public static final String CREDIT_NOT_ENOUGH_SALARY= "SALARY NOT ENOUGH";
    public static final String CREDIT_CONSUMPTION_AMOUNT_EXCEEDED= "MAXIMUM AMOUNT FOR CONSUMPTION CREDIT EXCEEDED";
    public static final String CREDIT_CONSUMPTION_CREDIT_TERM_EXCEEDED= "MAXIMUM CREDIT TERM FOR CONSUMPTION CREDIT EXCEEDED";
    public static final String CREDIT_CAR_CREDIT_TERM_EXCEEDED= "MAXIMUM CREDIT TERM FOR CAR CREDIT EXCEEDED";
    public static final String CREDIT_HOME_CREDIT_TERM_EXCEEDED= "MAXIMUM CREDIT TERM FOR HOME CREDIT EXCEEDED";
	public static final String CAR_LOAN_MIN_YEARS = "CAR LOAN SHOULD BE ON 3 YERAS OR MORE";
	public static final String CAR_LOAN_MAX_YEARS = "CAR LOAN COULDN'T BE ON MORE THAN 6 YEARS";
	public static final String HOME_LOAN_MIN_YEARS = "HOME LOAN SHOULD BE ON MORE THAN 15 YEARS";
	public static final String HOME_LOAN_MAX_YEARS = "HOME LOAN COULDN't BE ON MORE THAN 30 YEARS";

    
}

