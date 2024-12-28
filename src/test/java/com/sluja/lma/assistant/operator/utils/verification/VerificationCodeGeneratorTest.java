package com.sluja.lma.assistant.operator.utils.verification;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VerificationCodeGeneratorTest {

    @Test
    public void testGenerateCodeLength() {
        String code = VerificationCodeGenerator.generateCode();
        assertEquals(6, code.length(), "Generated code should have a length of 6");
    }

    @Test
    public void testGenerateCodeCharacters() {
        String code = VerificationCodeGenerator.generateCode();
        final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?!";
        for (char c : code.toCharArray()) {
            assertTrue(ALLOWED_CHARACTERS.indexOf(c) >= 0,
                    "Generated code contains invalid character: " + c);
        }
    }

    @Test
    public void testGenerateCodeUniqueness() {
        String code1 = VerificationCodeGenerator.generateCode();
        String code2 = VerificationCodeGenerator.generateCode();
        assertNotEquals(code1, code2, "Generated codes should be unique");
    }
}