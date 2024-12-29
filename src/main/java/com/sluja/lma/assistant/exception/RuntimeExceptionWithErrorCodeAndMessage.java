package com.sluja.lma.assistant.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public abstract class RuntimeExceptionWithErrorCodeAndMessage extends RuntimeException {

    private final Long errorCode;
    private final Map<String, List<String>> errorMessagesWithAdditionalInformation;
    // private final List<String> errorMessages;
    // private List<String> additionalInformation;

    public RuntimeExceptionWithErrorCodeAndMessage(final Long errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessagesWithAdditionalInformation = Map.of(errorMessage, List.of());
        // this.errorMessages = List.of(errorMessage);
        // this.additionalInformation = List.of();
    }

    public RuntimeExceptionWithErrorCodeAndMessage(final Long errorCode,
            final ExceptionMessageWithAdditionalInformation exceptionMessageWithAdditionalInformation) {
        this(errorCode, exceptionMessageWithAdditionalInformation.errorMessage());
        this.errorMessagesWithAdditionalInformation.put(exceptionMessageWithAdditionalInformation.errorMessage(),
                exceptionMessageWithAdditionalInformation.additionalInformation());
    }

    public RuntimeExceptionWithErrorCodeAndMessage(final Long errorCode, final List<String> errorMessages,
            final ExceptionMessageWithAdditionalInformation exceptionMessageWithAdditionalInformation) {
        super();
        this.errorCode = errorCode;
        this.errorMessagesWithAdditionalInformation = errorMessages.stream()
                .map(errorMessage -> Map.entry(errorMessage, new ArrayList<String>()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        this.errorMessagesWithAdditionalInformation.put(exceptionMessageWithAdditionalInformation.errorMessage(),
                exceptionMessageWithAdditionalInformation.additionalInformation());
    }

    public RuntimeExceptionWithErrorCodeAndMessage(final Long errorCode,
            final List<ExceptionMessageWithAdditionalInformation> errorMessagesWithAdditionalInformation) {
        super();
        this.errorCode = errorCode;
        this.errorMessagesWithAdditionalInformation = errorMessagesWithAdditionalInformation.stream()
                .map(exceptionMessageWithAdditionalInformation -> Map.entry(
                        exceptionMessageWithAdditionalInformation.errorMessage(),
                        exceptionMessageWithAdditionalInformation.additionalInformation()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
