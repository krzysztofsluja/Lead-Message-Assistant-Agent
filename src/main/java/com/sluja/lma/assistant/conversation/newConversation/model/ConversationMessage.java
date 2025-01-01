package com.sluja.lma.assistant.conversation.newConversation.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.sluja.lma.assistant.conversation.newConversation.enums.MessageAuthor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "LMA_CONVERSATION_MESSAGE")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ConversationMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "CON_MESSAGE_AUTHOR")
    @Enumerated(EnumType.STRING)
    private MessageAuthor author;
    @Column(name = "CON_CONVERSATION")
    @ManyToOne
    private Conversation conversation;
    @CreationTimestamp
    @Column(name = "CON_CREATION_DATE")
    private LocalDateTime creationDate;
}
