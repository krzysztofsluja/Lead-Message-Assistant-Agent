package com.sluja.lma.assistant.operator.utils.verification;

import java.security.SecureRandom;

public final class VerificationCodeGenerator {

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?!";
    private static final int CODE_LENGTH = 6;

    private VerificationCodeGenerator() {
    }

    public static String generateCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return code.toString();
    }

}
