package com.sluja.lma.assistant.conversation.newConversation.dto;

import java.util.List;

public record ContextMessageDTO(String content,
        String mainConcept,
        List<String> keywords,
        Long conversationId) {
}
