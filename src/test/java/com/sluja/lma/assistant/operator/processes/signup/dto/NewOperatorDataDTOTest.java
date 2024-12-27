package com.sluja.lma.assistant.operator.processes.signup.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NewOperatorDataDTOTest {

    private static final String VALID_FIRST_NAME = "John";
    private static final String VALID_LAST_NAME = "Doe";
    private static final String VALID_USERNAME = "john.doe";
    private static final String VALID_PASSWORD = "P@ssw0rd123";
    private static final String VALID_EMAIL = "john.doe@example.com";

    @Test
    void shouldCreateValidDTO() {
        NewOperatorDataDTO dto = NewOperatorDataDTO.of(
                VALID_FIRST_NAME,
                VALID_LAST_NAME,
                VALID_USERNAME,
                VALID_PASSWORD,
                VALID_EMAIL);

        assertAll(
                () -> assertEquals(VALID_FIRST_NAME, dto.firstName()),
                () -> assertEquals(VALID_LAST_NAME, dto.lastName()),
                () -> assertEquals(VALID_USERNAME, dto.username()),
                () -> assertEquals(VALID_PASSWORD, dto.password()),
                () -> assertEquals(VALID_EMAIL, dto.email()));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " ", "  " })
    void shouldThrowExceptionForInvalidFirstName(String firstName) {
        assertThrows(IllegalArgumentException.class,
                () -> NewOperatorDataDTO.of(firstName, VALID_LAST_NAME, VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL));
    }

    @Test
    void shouldTrimWhitespace() {
        NewOperatorDataDTO dto = NewOperatorDataDTO.of(
                " John ",
                " Doe ",
                " john.doe ",
                VALID_PASSWORD,
                " john.doe@example.com ");

        assertAll(
                () -> assertEquals("John", dto.firstName()),
                () -> assertEquals("Doe", dto.lastName()),
                () -> assertEquals("john.doe", dto.username()),
                () -> assertEquals(VALID_PASSWORD, dto.password()),
                () -> assertEquals("john.doe@example.com", dto.email()));
    }

    @Test
    void shouldValidateUsernameFormat() {
        assertAll(
                () -> assertDoesNotThrow(() -> NewOperatorDataDTO.of(VALID_FIRST_NAME, VALID_LAST_NAME, "john.doe",
                        VALID_PASSWORD, VALID_EMAIL)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> NewOperatorDataDTO.of(VALID_FIRST_NAME, VALID_LAST_NAME, "john@doe", VALID_PASSWORD,
                                VALID_EMAIL)),
                () -> assertThrows(IllegalArgumentException.class, () -> NewOperatorDataDTO.of(VALID_FIRST_NAME,
                        VALID_LAST_NAME, "john doe", VALID_PASSWORD, VALID_EMAIL)));
    }

    @Test
    void shouldEnsureValueEquality() {
        NewOperatorDataDTO dto1 = NewOperatorDataDTO.of(
                VALID_FIRST_NAME, VALID_LAST_NAME, VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL);
        NewOperatorDataDTO dto2 = NewOperatorDataDTO.of(
                VALID_FIRST_NAME, VALID_LAST_NAME, VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL);
        NewOperatorDataDTO dto3 = NewOperatorDataDTO.of(
                "Jane", VALID_LAST_NAME, VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL);

        assertAll(
                () -> assertEquals(dto1, dto2),
                () -> assertNotEquals(dto1, dto3),
                () -> assertEquals(dto1.hashCode(), dto2.hashCode()),
                () -> assertNotEquals(dto1.hashCode(), dto3.hashCode()));
    }
}