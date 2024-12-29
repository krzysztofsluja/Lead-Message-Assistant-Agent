package com.sluja.lma.assistant.exception.handlers;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;
import com.sluja.lma.assistant.exception.dto.ErrorResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Order
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

        private final MessageSource messageSource;
        private static final String GENERAL_ERROR_CODE = "error.general";
        private static final String DEFAULT_ERROR_MESSAGE = "An error occurred... Please try later";
        private static final String ERROR_PREFIX = "error.";

        @ExceptionHandler(ExceptionWithErrorCodeAndMessage.class)
        public ResponseEntity<ErrorResponseDTO> handleExceptionWithErrorCodeAndMessage(
                        ExceptionWithErrorCodeAndMessage ex) {
                log.error("Handling general exception with error code: {}", ex.getErrorCode(), ex);

                try {
                        Map<String, List<String>> translatedErrors = ex.getErrorMessagesWithAdditionalInformation()
                                        .entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(
                                                        entry -> {
                                                                String messageKey = entry.getKey();
                                                                if (!messageKey.startsWith(ERROR_PREFIX)) {
                                                                        return messageKey;
                                                                }
                                                                try {
                                                                        return messageSource.getMessage(messageKey,
                                                                                        null, Locale.getDefault());
                                                                } catch (NoSuchMessageException e) {
                                                                        log.warn("Message key not found: {}",
                                                                                        messageKey);
                                                                        return messageSource.getMessage(
                                                                                        GENERAL_ERROR_CODE,
                                                                                        null,
                                                                                        DEFAULT_ERROR_MESSAGE,
                                                                                        Locale.getDefault());
                                                                }
                                                        },
                                                        Map.Entry::getValue));
                        return ResponseEntity
                                        .badRequest()
                                        .body(new ErrorResponseDTO(
                                                        ex.getErrorCode(),
                                                        translatedErrors));
                } catch (Exception e) {
                        log.error("Unexpected error while handling exception", e);
                        String generalError = messageSource.getMessage(GENERAL_ERROR_CODE, null, DEFAULT_ERROR_MESSAGE,
                                        Locale.getDefault());

                        return ResponseEntity
                                        .badRequest()
                                        .body(new ErrorResponseDTO(1L, Map.of(generalError, List.of())));
                }
        }
}