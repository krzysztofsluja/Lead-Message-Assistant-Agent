package com.sluja.lma.assistant.operator.processes.verification.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "LMA_OPERATORS_VERIFICATION_CODES")
public class OperatorVerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPVC_ID")
    private Long id;

    @Column(name = "OPVC_CODE")
    private String code;

    @Column(name = "OPVC_EXPIRATION_DATE")
    private LocalDateTime expirationDate;

    @Column(name = "OPVC_IS_USED")
    private boolean used = false;

    @Column(name = "OPVC_CREATED_DATE")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "OPVC_UPDATED_DATE")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "OPVC_TYPE")
    @Enumerated(EnumType.STRING)
    private VerificationType type;
}
