package com.sluja.lma.assistant.operator.processes.signup.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "LMA_OPERATORS")
@Data
public class OperatorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPD_ID")
    private Long id;

    @Column(name = "OPD_FIRST_NAME", nullable = false)
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "OPD_LAST_NAME", nullable = false)
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "OPD_USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "OPD_PASSWORD", nullable = false)
    private String password;

    @Column(name = "OPD_EMAIL", nullable = false, unique = true)
    @Email(message = "Invalid email address")
    private String email;

    @Column(name = "OPD_IS_OPERATOR_ACTIVE")
    private boolean active = false;

    @Column(name = "OPD_IS_OPERATOR_VALIDATED")
    private boolean validate = false;

    @Column(name = "OPD_CREATED_DATE")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "OPD_UPDATED_DATE")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "OPD_LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    @Column(name = "OPD_ACTIVE_DATE")
    private LocalDateTime activeDate;

    @Column(name = "OPD_VALIDATE_DATE")
    private LocalDateTime validateDate;

    @Column(name = "OPD_LAST_PASSWORD_CHANGE_DATE")
    private LocalDateTime lastPasswordChangeDate;

    @Column(name = "OPD_ACCOUNT_LOCKED")
    private boolean accountLocked = false;

    @Column(name = "OPD_ACCOUNT_LOCKED_UNTIL")
    private LocalDateTime accountLockedUntil;
}
