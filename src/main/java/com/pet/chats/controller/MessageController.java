package com.pet.chats.controller;

import com.pet.chats.dto.ChatMessageRequest;
import com.pet.chats.dto.ChatMessageResponse;
import com.pet.chats.entity.MessageEntity;
import com.pet.chats.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageRequest request) {
        MessageEntity savedMessage = messageService.saveMessage(
                request.getChatId(),
                request.getSenderId(),
                request.getContent()
        );

        ChatMessageResponse response = ChatMessageResponse.builder()
                .id(savedMessage.getId())
                .chatId(savedMessage.getChat().getId())
                .senderId(savedMessage.getSender().getId())
                .senderUsername(savedMessage.getSender().getUsername())
                .content(savedMessage.getContent())
                .sentAt(savedMessage.getSentAt())
                .build();

        messagingTemplate.convertAndSend("/topic/chat/" + request.getChatId(), response);
    }
}
