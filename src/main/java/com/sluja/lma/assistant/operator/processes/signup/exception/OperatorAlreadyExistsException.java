package com.sluja.lma.assistant.operator.processes.signup.exception;

import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;

public class OperatorAlreadyExistsException extends ExceptionWithErrorCodeAndMessage {

    public OperatorAlreadyExistsException() {
        super(1002L, "error.operator.signup.alreadyExists");
    }
}
