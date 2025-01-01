package com.sluja.lma.assistant.conversation.newConversation.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.sluja.lma.assistant.conversation.newConversation.enums.ConversationType;
import com.sluja.lma.assistant.operator.processes.signup.model.OperatorData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LMA_CONVERSATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CON_TYPE")
    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;
    @Column(name = "CON_USERNAME")
    // TODO encrypt this field
    private String username;
    @Column(name = "CON_PROFILE_USERNAME")
    // TODO encrypt this field
    private String profileUsername;
    @Column(name = "CON_OPERATOR")
    @ManyToOne
    private OperatorData operator;
    @CreationTimestamp
    @Column(name = "CON_CREATION_DATE")
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "conversation")
    private List<ConversationMessage> messages;
}
