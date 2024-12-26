package com.sluja.lma.assistant.operator.processes.signup.dto;

import org.apache.commons.lang3.StringUtils;

import com.sluja.lma.assistant.operator.utils.validation.ValidationPatterns;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewOperatorDataDTO(
                @NotBlank(message = "First name is required") @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters") String firstName,

                @NotBlank(message = "Last name is required") @Size(min = 2, max = 9, message = "Last name must be between 2 and 50 characters") String lastName,

                @NotBlank(message = "Username is required") @Size(min = 3, max = 50, message = "Username must be between 3 and 9 characters") @Pattern(regexp = ValidationPatterns.USERNAME_PATTERN, message = ValidationPatterns.USERNAME_MESSAGE) String username,

                @NotBlank(message = "Password is required") @Pattern(regexp = ValidationPatterns.PASSWORD_PATTERN, message = ValidationPatterns.PASSWORD_MESSAGE) String password,

                @NotBlank(message = "Email is required") @Email(message = ValidationPatterns.EMAIL_MESSAGE) String email) {

        public NewOperatorDataDTO {
                if (StringUtils.isAnyBlank(firstName, lastName, username, password, email)) {
                        throw new IllegalArgumentException("All fields are required");
                }

                firstName = firstName.trim();
                lastName = lastName.trim();
                username = username.trim();
                email = email.trim();

                if (!username.matches(ValidationPatterns.USERNAME_PATTERN))
                        throw new IllegalArgumentException(ValidationPatterns.USERNAME_MESSAGE);

                if (!password.matches(ValidationPatterns.PASSWORD_PATTERN))
                        throw new IllegalArgumentException(ValidationPatterns.PASSWORD_MESSAGE);

                if (!email.matches(ValidationPatterns.EMAIL_PATTERN))
                        throw new IllegalArgumentException(ValidationPatterns.EMAIL_MESSAGE);
        }

        public static NewOperatorDataDTO of(final String firstName,
                        final String lastName,
                        final String username,
                        final String password,
                        final String email) {
                return new NewOperatorDataDTO(firstName, lastName, username, password, email);
        }
}
