package com.pet.chats.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ChatMessageResponse {
    private int id;
    private int chatId;
    private int senderId;
    private String senderUsername;
    private String content;
    private OffsetDateTime sentAt;
}
