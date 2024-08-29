package com.dzo.test_bank.util;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessageUtil {
    private static final String EMAIL_KEY = "users_email_key";
    private static final String USER_ID_KEY = "accounts_user_id_fkey";
    private static final String CREATE_BY_USER_ID_NULL_KEY = "null value in column create_by_user_id";
    private static final String OPERATION_TRANSACTION_TYPE_NULL_KEY = "entity.Operation.getTransactionType is null";
    private static final String OPERATION_TRANSACTION_AMOUNT_NULL_KEY = "entity.Operation.getTransactionAmount is null";
    private static final String ACCOUNT_NULL_KEY = "entity.Account.getCurrentBalance because";
    private static final String ACCOUNT_NAME_NUM_KEY = "accounts_account_name_account_num_key";
    private static final String FIRST_NAME_NULL_KEY = "null value in column first_name";
    private static final String LAST_NAME_NULL_KEY = "null value in column last_name";
    private static final String EMAIL_NULL = "null value in column email";
    private static final String DATE_OF_BIRTH_NULL_KEY = "null value in column date_of_birth";
    private static final String OVERDRAFTS_KEY = "overdrafts_on_the_account";
    private static final String USER_ID_NULL_KEY = "because user is null";
    private static final String CREATE_USER_ID_NULL_KEY = "operations_create_by_user_id_fkey";
    private static final String CONSTRAIN_MIN_VALIDATION_KEY = "model.entity.Operation, messageTemplate=jakarta.validation.constraints.Min.message";

    private static final String EMAIL_ERROR_MESSAGE = "The email address is already in use.";
    private static final String USER_NOT_FOUND_MESSAGE = "User not found";
    private static final String USER_NOT_PRESENT_MESSAGE = "User is not present";
    private static final String TRANSACTION_TYPE_NOT_PRESENT_MESSAGE = "Transaction type is not present";
    private static final String TRANSACTION_AMOUNT_NOT_PRESENT_MESSAGE = "Transaction amount is not present";
    private static final String ACCOUNT_SETUP_NOT_ALLOWED_MESSAGE = "Account setup not allowed";
    private static final String FIRST_NAME_NOT_PRESENT_MESSAGE = "First name is not present";
    private static final String LAST_NAME_NOT_PRESENT_MESSAGE = "Last name is not present";
    private static final String EMAIL_NOT_PRESENT_MESSAGE = "Email is not present";
    private static final String DATE_OF_BIRTH_NOT_PRESENT_MESSAGE = "Date of birth is not present";
    private static final String OVERDRAFTS_NOT_ACCEPTED_MESSAGE = "Overdrafts on the account are not accepted";
    private static final String INTEGRITY_VIOLATION_MESSAGE = "Data integrity violation: ";

    public static Map<String, String> generateErrorMessage(String errorMessage) {
        Map<String, String> mapErrors = new HashMap<>();
        String cleanedMessage = cleanErrorMessage(errorMessage);

        checkEmailError(cleanedMessage, mapErrors);
        checkUserErrors(cleanedMessage, mapErrors);
        checkOperationErrors(cleanedMessage, mapErrors);
        checkAccountErrors(cleanedMessage, mapErrors);
        checkUserDetailErrors(cleanedMessage, mapErrors);
        checkOverdraftErrors(cleanedMessage, mapErrors);

        if (mapErrors.isEmpty()) mapErrors.put("integrity", INTEGRITY_VIOLATION_MESSAGE + cleanedMessage);
        return mapErrors;
    }

    private static String cleanErrorMessage(String errorMessage) {
        return errorMessage
                .replace("\"", "")
                .replace("()", "")
                .replace("{", "")
                .replace("}", "")
                .replace("'", "");
    }

    private static void checkEmailError(String message, Map<String, String> errors) {
        if (message.contains(EMAIL_KEY)) errors.put("email", EMAIL_ERROR_MESSAGE);
    }

    private static void checkUserErrors(String message, Map<String, String> errors) {
        if (message.contains(USER_ID_KEY) || message.contains(USER_ID_NULL_KEY) || message.contains(CREATE_USER_ID_NULL_KEY) )
            errors.put("User", USER_NOT_FOUND_MESSAGE);
        if (message.contains(CREATE_BY_USER_ID_NULL_KEY)) errors.put("User", USER_NOT_PRESENT_MESSAGE);
    }

    private static void checkOperationErrors(String message, Map<String, String> errors) {
        if (message.contains(OPERATION_TRANSACTION_TYPE_NULL_KEY)) errors.put("Operation", TRANSACTION_TYPE_NOT_PRESENT_MESSAGE);
        if (message.contains(OPERATION_TRANSACTION_AMOUNT_NULL_KEY)) errors.put("Operation", TRANSACTION_AMOUNT_NOT_PRESENT_MESSAGE);
    }

    private static void checkAccountErrors(String message, Map<String, String> errors) {
        if (message.contains(ACCOUNT_NULL_KEY) || message.contains(ACCOUNT_NAME_NUM_KEY))
            errors.put("Account", ACCOUNT_SETUP_NOT_ALLOWED_MESSAGE);
    }

    private static void checkUserDetailErrors(String message, Map<String, String> errors) {
        if (message.contains(FIRST_NAME_NULL_KEY)) errors.put("User", FIRST_NAME_NOT_PRESENT_MESSAGE);
        if (message.contains(LAST_NAME_NULL_KEY)) errors.put("User", LAST_NAME_NOT_PRESENT_MESSAGE);
        if (message.contains(EMAIL_NULL)) errors.put("User", EMAIL_NOT_PRESENT_MESSAGE);
        if (message.contains(DATE_OF_BIRTH_NULL_KEY)) errors.put("User", DATE_OF_BIRTH_NOT_PRESENT_MESSAGE);
    }

    private static void checkOverdraftErrors(String message, Map<String, String> errors) {
        if (message.contains(OVERDRAFTS_KEY) || message.contains(CONSTRAIN_MIN_VALIDATION_KEY))
            errors.put("Account", OVERDRAFTS_NOT_ACCEPTED_MESSAGE);
    }
}
