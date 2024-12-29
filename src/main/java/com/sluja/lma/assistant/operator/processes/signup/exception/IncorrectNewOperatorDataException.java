package com.sluja.lma.assistant.operator.processes.signup.exception;

import java.util.List;

import com.sluja.lma.assistant.exception.ExceptionMessageWithAdditionalInformation;
import com.sluja.lma.assistant.exception.RuntimeExceptionWithErrorCodeAndMessage;

public class IncorrectNewOperatorDataException extends RuntimeExceptionWithErrorCodeAndMessage {

    public IncorrectNewOperatorDataException(
            final List<ExceptionMessageWithAdditionalInformation> errorMessagesWithAdditionalInformation) {
        super(1004L, errorMessagesWithAdditionalInformation);
    }
}
