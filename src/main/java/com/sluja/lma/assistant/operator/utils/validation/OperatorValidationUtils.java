package com.sluja.lma.assistant.operator.utils.validation;

public final class OperatorValidationUtils {

    private OperatorValidationUtils() {
    }

    public static boolean isUsernameValid(final String username) {
        return username.matches(ValidationPatterns.USERNAME_PATTERN);
    }

    public static boolean isPasswordValid(final String password) {
        return password.matches(ValidationPatterns.PASSWORD_PATTERN);
    }

    public static boolean isEmailValid(final String email) {
        return email.matches(ValidationPatterns.EMAIL_PATTERN);
    }

    public static boolean isUsernameInvalid(final String username) {
        return !isUsernameValid(username);
    }

    public static boolean isPasswordInvalid(final String password) {
        return !isPasswordValid(password);
    }

    public static boolean isEmailInvalid(final String email) {
        return !isEmailValid(email);
    }
}
