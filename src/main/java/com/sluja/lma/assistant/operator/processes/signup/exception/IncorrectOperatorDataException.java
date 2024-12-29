package com.sluja.lma.assistant.operator.processes.signup.exception;

import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;

public class IncorrectOperatorDataException extends ExceptionWithErrorCodeAndMessage {

    public IncorrectOperatorDataException() {
        super(1001L, "error.operator.signup.incorrect.data");
    }
}
