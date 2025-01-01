package com.sluja.lma.assistant.conversation.newConversation.dto;

import java.util.List;

import com.sluja.lma.assistant.conversation.newConversation.enums.ConversationType;

public record ConversationDTO(
        Long id,
        String username,
        String profileUsername,
        Long operatorId,
        ConversationType conversationType,
        List<MessageDTO> operatorMessages,
        List<MessageDTO> modelMessages,
        List<String> keywords) {

}
