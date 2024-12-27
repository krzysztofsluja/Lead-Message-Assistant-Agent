package com.sluja.lma.assistant.operator.utils.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordStrengthCheckerTest {

    @Test
    void shouldIdentifyStrongPassword() {
        // given
        String strongPassword = "qP^V9$5q@n=3";

        // when
        PasswordStrengthChecker.PasswordStrengthResult result = PasswordStrengthChecker
                .checkPasswordStrength(strongPassword);

        // then
        assertTrue(result.isStrong());
        assertTrue(result.getSuggestionsAsString().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "password",
            "12345678",
            "abcdefgh",
            "ABCDEFGH",
            "pass123",
            "password123"
    })
    void shouldIdentifyWeakPasswords(String weakPassword) {
        // when
        PasswordStrengthChecker.PasswordStrengthResult result = PasswordStrengthChecker
                .checkPasswordStrength(weakPassword);

        // then
        assertFalse(result.isStrong());
        assertFalse(result.getSuggestionsAsString().isEmpty());
    }

    @Test
    void shouldFormatSuggestionsCorrectly() {
        // given
        String weakPassword = "password123";

        // when
        PasswordStrengthChecker.PasswordStrengthResult result = PasswordStrengthChecker
                .checkPasswordStrength(weakPassword);

        // then
        String suggestions = result.getSuggestionsAsString();
        assertAll(
                () -> assertFalse(suggestions.isEmpty()),
                () -> assertTrue(suggestions.contains("1. ")),
                () -> assertFalse(suggestions.endsWith(", ")));
    }

    @Test
    void shouldHandleEmptySuggestions() {
        // given
        String strongPassword = "qP^V9$5q@n=3";

        // when
        PasswordStrengthChecker.PasswordStrengthResult result = PasswordStrengthChecker
                .checkPasswordStrength(strongPassword);

        // then
        assertEquals("", result.getSuggestionsAsString());
    }

    @Test
    void shouldProvideUsefulSuggestionsForWeakPasswords() {
        // given
        String weakPassword = "password123";

        // when
        PasswordStrengthChecker.PasswordStrengthResult result = PasswordStrengthChecker
                .checkPasswordStrength(weakPassword);

        // then
        String suggestions = result.getSuggestionsAsString();
        assertAll(
                () -> assertFalse(result.isStrong()),
                () -> assertNotNull(suggestions),
                () -> assertFalse(suggestions.isEmpty()));
    }

    @Test
    void shouldIdentifyCommonPasswords() {
        // given
        String[] commonPasswords = {
                "password123",
                "qwerty123",
                "admin123",
                "letmein123"
        };

        // then
        for (String password : commonPasswords) {
            PasswordStrengthChecker.PasswordStrengthResult result = PasswordStrengthChecker
                    .checkPasswordStrength(password);
            assertFalse(result.isStrong(), "Password should be considered weak: " + password);
        }
    }
}