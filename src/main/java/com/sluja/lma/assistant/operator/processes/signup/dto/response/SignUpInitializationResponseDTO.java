package com.sluja.lma.assistant.operator.processes.signup.dto.response;

public record SignUpInitializationResponseDTO(
        String operatorId,
        String message) {
    public static SignUpInitializationResponseDTO of(String operatorId) {
        return new SignUpInitializationResponseDTO(
                operatorId,
                "Operator signup initialized successfully");
    }
}