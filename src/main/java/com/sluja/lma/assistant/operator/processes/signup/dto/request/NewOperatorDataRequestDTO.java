package com.sluja.lma.assistant.operator.processes.signup.dto.request;

import java.io.Serializable;

public record NewOperatorDataRequestDTO(String firstName,
                String lastName,
                String username,
                String password,
                String email) implements Serializable {

        @Override
        public String toString() {
                return "NewOperatorDataRequestDTO{" +
                                "firstName='" + firstName + '\'' +
                                ", lastName='" + lastName + '\'' +
                                ", username='" + username + '\'' +
                                ", email='" + email + '\'' +
                                '}';
        }

}
