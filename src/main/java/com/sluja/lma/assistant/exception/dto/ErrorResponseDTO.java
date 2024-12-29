package com.sluja.lma.assistant.exception.dto;

import java.util.List;
import java.util.Map;

public record ErrorResponseDTO(
    Long errorCode,
    Map<String, List<String>> errors
) {}