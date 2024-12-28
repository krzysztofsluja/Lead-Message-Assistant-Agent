package com.sluja.lma.assistant.operator.processes.verification.dto;

import java.io.Serializable;

import com.sluja.lma.assistant.operator.processes.verification.enums.VerificationType;
import com.sluja.lma.assistant.operator.utils.verification.VerificationCodeGenerator;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VerificationCodeDTO(
        @Size(min = 6, max = 6, message = "Code must be exactly 6 characters long") @Pattern(regexp = "^[A-Z0-9?!]{6}$") String code,
        VerificationType type) implements Serializable {

    public VerificationCodeDTO(final VerificationType type) {
        this(VerificationCodeGenerator.generateCode(), type);
    }

}
