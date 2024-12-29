package com.sluja.lma.assistant.operator.processes.signup.exception;

import java.util.List;
import java.util.Map;

import com.sluja.lma.assistant.exception.ExceptionMessageWithAdditionalInformation;
import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;

public class OperatorSignUpInterruptedException extends ExceptionWithErrorCodeAndMessage {

    public OperatorSignUpInterruptedException(
            final List<ExceptionMessageWithAdditionalInformation> errorMessagesWithAdditionalInformation) {
        super(1003L, errorMessagesWithAdditionalInformation);
    }

    public OperatorSignUpInterruptedException(final Map<String, List<String>> errorMessagesWithAdditionalInformation) {
        super(1003L, errorMessagesWithAdditionalInformation);
    }

    public OperatorSignUpInterruptedException() {
        super(1003L, "error.operator.signup.interrupted");
    }
}
