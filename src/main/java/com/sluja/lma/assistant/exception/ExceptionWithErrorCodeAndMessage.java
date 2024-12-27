package com.sluja.lma.assistant.exception;

import lombok.Getter;

@Getter
public class ExceptionWithErrorCodeAndMessage extends Exception {

    private final Long errorCode;
    private final String errorMessage;

    public ExceptionWithErrorCodeAndMessage(final Long errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
