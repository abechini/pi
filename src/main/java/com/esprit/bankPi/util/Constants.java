package com.esprit.bankPi.util;

public class Constants {
	// API
    public static final String API_SECRET = "Zq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A?";
    public static final String API_AUTH_HEADER = "Authorization";
    public static final String API_TOKEN_BEGIN = "Bearer ";

    // Has Role
    public static final String HAS_ROLE_USER = "hasRole('" + Patterns.ROLE_USER + "')";
    public static final String HAS_ROLE_ADMIN = "hasRole('" + Patterns.ROLE_ADMIN + "')";
    public static final String HAS_ROLE_ADVISOR = "hasRole('" + Patterns.ROLE_ADVISOR + "')";
    public static final String HAS_ROLE_ENTREPRISE = "hasRole('" + Patterns.ROLE_ENTREPRISE + "')";
    public static final String HAS_ROLE_ASSOCIATION = "hasRole('" + Patterns.ROLE_ASSOCIATION + "')";

    // User
    public static final String URI_API_ALL = "/api/**";
    public static final String URI_API_HEALTH = "/api/health";
    public static final String URI_API_AUTH = "/api/auth";
    public static final String URI_API_USER = "/api/user";
    public static final String URI_API_USER_REGISTRATION = "/register";
    public static final String URI_API_USER_ALL = "/api/users";
    public static final String URI_API_USER_FIND = "/api/user/find";
    public static final String URI_API_USER_ID = "/api/user/{id}";
    public static final String URI_API_USER_PROFILE = "/api/user/{id}/profile";
    public static final String URI_API_CURRENT_USER_PROFILE = "/api/user/profile";
    public static final String URI_API_USER_CHANGE_PASSWORD = "/api/user/password";
    public static final String URI_API_USER_SET_PASS = "/api/user/{id}/password";
    public static final String URI_API_USER_ENABLE = "/api/user/{id}/state/enable";
    public static final String URI_API_USER_UNLOCK = "/api/user/{id}/state/unlock";
    public static final String URI_API_USER_UNEXPIRE = "/api/user/{id}/state/unexpire";
    public static final String URI_API_USER_PASSWORD_UNEXPIRE = "/api/user/{id}/password/unexpire";
    public static final String URI_API_USER_SIGNATURE_VERIFYING = "/api/user/SignatureVerifying";
    public static final String URI_API_USER_SIGNATURE_SIGNING = "/api/user/SignatureSigning";
    public static final String URI_API_USER_STATISTICS = "/api/statistics";

    // Account
    public static final String URI_API_ACCOUNT_ALL = "/api/accounts";
    public static final String URI_API_ACCOUNT = "/api/account/{id}";
    public static final String URI_API_ACCOUNT_OWNER = "/api/account/{id}/owner";
    public static final String URI_API_ACCOUNT_CHECKING_ALL = "/api/account/checking";
    public static final String URI_API_ACCOUNT_SAVINGS_ALL = "/api/account/savings";
    public static final String URI_API_ACCOUNT_INVESTS_ALL = "/api/account/invests";
    public static final String URI_API_ACCOUNT_COLLECT_ALL = "/api/account/collects";
    public static final String URI_API_USER_ACCOUNT = "/api/user/{id}/account";
    public static final String URI_API_USER_ACCOUNT_CURRENT = "/api/user/accounts";
    public static final String URI_API_USER_ACCOUNT_CHECKING = "/api/user/{id}/account/checking";
    public static final String URI_API_USER_ACCOUNT_SAVINGS = "/api/user/{id}/account/savings";
    public static final String URI_API_USER_ACCOUNT_INVESTS = "/api/user/{id}/account/invests";
    public static final String URI_API_USER_ACCOUNT_COLLECT = "/api/user/{id}/account/collects";
    public static final String URI_API_USER_ACCOUNT_CHECKING_CURRENT = "/api/user/account/checking";
    public static final String URI_API_USER_ACCOUNT_SAVINGS_CURRENT = "/api/user/account/savings";
    public static final String URI_API_USER_ACCOUNT_INVESTS_CURRENT = "/api/user/account/invests";
    public static final String URI_API_USER_ACCOUNT_COLLECT_CURRENT = "/api/user/account/collects";
    public static final String URI_API_ACCOUNT_TRANSACTION = "/api/account/{id}/transaction";
    public static final String URI_API_ACCOUNT_TRANSFER = "/api/account/{id}/transfer";
    public static final String URI_API_ACCOUNT_CREDIT_CARD = "/api/account/{id}/generateCreditCard";
    public static final String URI_API_ACCOUNT_PARRAINAGE = "/api/account/{id}/parrainage";
    public static final String URI_API_ACCOUNT_EARNINGS = "/api/account/{id}/earnings";
    public static final String URI_API_SAVINGS_EARNINGS = "/api/account/earnings";


    public static final String PATH_VARIABLE_ID = "id";

    //Promotion
    public static final String URI_API_ACCOUNT_PROMOTION = "/api/accountpromotions";
    public static final String URI_API_ACCOUNT_PROMOTION_ADD = "/addAccountPromotions";
    public static final String URI_API_ACCOUNT_PROMOTION_UPDATE = "/updateAccountPromotions/{id}";
    public static final String URI_API_ACCOUNT_PROMOTION_FIND = "/getAccountPromotionsById/{idAccountPromotions}";
    public static final String URI_API_ACCOUNT_PROMOTION_DELETE = "/deleteAccountPromotionsById/{idAccountPromotions}";
    public static final String URI_API_ACCOUNT_PROMOTION_ALL = "/getAccountPromotions";
    public static final String URI_API_CREDIT_PROMOTION = "/api/creditpromotions";
    public static final String URI_API_CREDIT_PROMOTION_ADD = "/addCreditPromotions";
    public static final String URI_API_CREDIT_PROMOTION_UPDATE = "/updateCreditPromotions/{id}";
    public static final String URI_API_CREDIT_PROMOTION_FIND = "/getCreditPromotionsById/{idCreditPromotions}";
    public static final String URI_API_CREDIT_PROMOTION_DELETE = "/deleteCreditPromotionsById/{idCreditPromotions}";
    public static final String URI_API_CREDIT_PROMOTION_ALL = "/getCreditPromotions";
    public static final String URI_API_PROMOTION = "/api/promotions";
    public static final String URI_API_PROMOTION_ALL = "/getPromotions";
    public static final String URI_API_VIRTUAL_CARD_PROMOTION = "/api/vitualcardpromotions";
    public static final String URI_API_VIRTUAL_CARD_PROMOTION_ADD = "/addVitualCardPromotions";
    public static final String URI_API_VIRTUAL_CARD_PROMOTION_UPDATE = "/updateVitualCardPromotions/{id}";
    public static final String URI_API_VIRTUAL_CARD_PROMOTION_FIND = "/getVitualCardPromotionsById/{idVitualCardPromotions}";
    public static final String URI_API_VIRTUAL_CARD_PROMOTION_DELETE = "/deleteVitualCardPromotionsById/{idVitualCardPromotions}";
    public static final String URI_API_VIRTUAL_CARD_PROMOTION_ALL = "/getVitualCardPromotions";


    // Account Type Categories
    public static final String ACCOUNT_CHECKING_CATEGORY = "CHK";
    public static final String ACCOUNT_SAVING_CATEGORY = "SAV";
    public static final String ACCOUNT_INVEST_CATEGORY = "INV";
    public static final String ACCOUNT_COLLECT_CATEGORY = "COL";

    // Transaction State Codes
    public static final String ACCOUNT_TRANSACTION_STATE_COMPLETE_CODE = "COM";

    // Transaction Type Codes
    public static final String ACCOUNT_TRANSACTION_TYPE_DEPOSIT_CODE = "DPT";
    public static final String ACCOUNT_TRANSACTION_TYPE_OVERDRAFT_FEE_CODE = "OVF";
    public static final String ACCOUNT_TRANSACTION_TYPE_TRANSFER_CODE = "TRN";

    // Transaction Category Codes
    public static final String ACCOUNT_TRANSACTION_CATEGORY_INCOME_CODE = "INC";
    public static final String ACCOUNT_TRANSACTION_CATEGORY_MISCELLANEOUS_CODE = "MIS";
    public static final String ACCOUNT_TRANSACTION_CATEGORY_FEE_CODE = "FEE";

    // Ownership Type Codes
    public static final String ACCOUNT_OWNER_INDIVIDUAL_CODE = "IND";
    public static final String ACCOUNT_OWNER_JOINT_CODE = "JNT";
    public static final String ACCOUNT_OWNER_ENTREPRISE_CODE = "ENT";
    public static final String ACCOUNT_OWNER_ASSOCIATION_CODE = "ASS";

    // Credit Variable
    public static final Double CREDIT_INTEREST = 0.12;
    public static final Double INSURANCE_PERCENTAGE = 0.12;
    public static final Double CREDIT_FEES = 250d;
}
