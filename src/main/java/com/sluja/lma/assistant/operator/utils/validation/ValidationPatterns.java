package com.sluja.lma.assistant.operator.utils.validation;

public final class ValidationPatterns {
    private ValidationPatterns() {
    }

    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]+$";
    public static final String USERNAME_MESSAGE = "Username can only contain letters, numbers, dots, underscores and hyphens";

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String PASSWORD_MESSAGE = "Password must be at least 8 characters long and contain at least one digit, one uppercase, one lowercase, and one special character";

    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String EMAIL_MESSAGE = "Invalid email format";

    public static final String PASSWORD_STRENGTH_MESSAGE = "Your password is not strong enough! Follow these suggestions: ";
}