package com.sluja.lma.assistant.operator.processes.signup.service.interfaces;

import com.sluja.lma.assistant.operator.processes.signup.dto.request.NewOperatorDataRequestDTO;
import com.sluja.lma.assistant.operator.processes.signup.dto.response.SignUpInitializationResponseDTO;

public interface IOperatorSignUp {
    SignUpInitializationResponseDTO initializeSignUp(NewOperatorDataRequestDTO newOperatorDataRequestDTO);
}
