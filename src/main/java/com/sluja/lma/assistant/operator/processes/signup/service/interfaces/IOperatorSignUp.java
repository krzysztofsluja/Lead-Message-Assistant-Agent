package com.sluja.lma.assistant.operator.processes.signup.service.interfaces;

import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;
import com.sluja.lma.assistant.operator.processes.signup.dto.request.NewOperatorDataRequestDTO;

public interface IOperatorSignUp {

    void initializeSignUp(final NewOperatorDataRequestDTO newOperatorDataRequestDTO)
            throws ExceptionWithErrorCodeAndMessage;
}
