package com.sluja.lma.assistant.operator.processes.verification.model;

import jakarta.persistence.*;
import lombok.Data;
import com.sluja.lma.assistant.operator.processes.signup.model.OperatorData;
import com.sluja.lma.assistant.operator.processes.verification.enums.VerificationStatus;

@Entity
@Data
@Table(name = "LMA_OPERATORS_VERIFICATION_PROCESSES")
public class OperatorVerificationProcess {

    @Id
    @Column(name = "OPVP_ID")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "OPVP_OPERATOR_ID")
    private OperatorData operator;

    @OneToOne
    @JoinColumn(name = "OPVP_CODE_ID")
    private OperatorVerificationCode verificationCode;

    @Column(name = "OPVP_CREATED_DATE")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "OPVP_UPDATED_DATE")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "OPVP_VERIFICATION_DATE")
    private LocalDateTime verificationDate;

    @Column(name = "OPVP_EXPIRATION_DATE")
    private LocalDateTime expirationDate;

    @Column(name = "OPVP_CANCEL_DATE")
    private LocalDateTime cancelDate;

    @Column(name = "OPVP_IS_ENDED")
    private boolean ended = false;

    @Column(name = "OPVP_STATUS")
    @Enumerated(EnumType.STRING)
    private VerificationStatus status = VerificationStatus.PENDING;

}
