package com.pet.chats.dto;

import lombok.Data;

@Data
public class ChatMessageRequest {
    private int chatId;
    private int senderId;
    private String content;
}
