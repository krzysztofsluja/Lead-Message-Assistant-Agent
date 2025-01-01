package com.sluja.lma.assistant.conversation.newConversation.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.sluja.lma.assistant.conversation.newConversation.enums.MessageAuthor;

public record MessageDTO(
        @NotEmpty(message = "Message content cannot be empty") String content,
        MessageAuthor author,
        LocalDateTime timestamp,
        @NotNull(message = "Conversation ID cannot be null") Long conversationId) {
}
