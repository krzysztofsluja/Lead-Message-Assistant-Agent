package com.sluja.lma.assistant.operator.processes.verification.dto;

import java.io.Serializable;

import com.sluja.lma.assistant.operator.processes.verification.enums.VerificationStatus;

public record VerificationProcessDTO(VerificationCodeDTO code,
                String username,
                VerificationStatus status) implements Serializable {

}
