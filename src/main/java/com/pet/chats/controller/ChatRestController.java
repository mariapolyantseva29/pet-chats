package com.pet.chats.controller;

import com.pet.chats.dto.ChatMessageResponse;
import com.pet.chats.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatRestController {
    private final MessageService messageService;

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<ChatMessageResponse>> getChatMessages(@PathVariable int chatId) {
        List<ChatMessageResponse> messages = messageService.getChatMessages(chatId).stream()
                .map(message -> ChatMessageResponse.builder()
                        .id(message.getId())
                        .chatId(message.getChat().getId())
                        .senderId(message.getSender().getId())
                        .senderUsername(message.getSender().getUsername())
                        .content(message.getContent())
                        .sentAt(message.getSentAt())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(messages);
    }
}
