package com.sluja.lma.assistant.exception;

import java.util.List;

public record ExceptionMessageWithAdditionalInformation(String errorMessage,
        List<String> additionalInformation) {

    public static ExceptionMessageWithAdditionalInformation of(final String errorMessage,
            final List<String> additionalInformation) {
        return new ExceptionMessageWithAdditionalInformation(errorMessage, additionalInformation);
    }

}
