package com.sluja.lma.assistant.operator.processes.signup.dto.request;

import java.io.Serializable;

public record NewOperatorDataRequestDTO(String firstName,
        String lastName,
        String username,
        String password,
        String email) implements Serializable {

}
