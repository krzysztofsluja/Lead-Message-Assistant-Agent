package com.sluja.lma.assistant.operator.utils.validation;

public final class ValidationPatterns {
    private ValidationPatterns() {
    }

    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9.]+$";
    public static final String USERNAME_MESSAGE_CODE = "error.operator.signup.username.invalid";

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?!^&+=])(?=\\S+$).{8,}$";
    public static final String PASSWORD_MESSAGE_CODE = "error.operator.signup.password.invalid";

    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String EMAIL_MESSAGE_CODE = "error.operator.signup.email.invalid";

    public static final String PASSWORD_STRENGTH_MESSAGE = "Your password is not strong enough! Follow these suggestions: ";
}