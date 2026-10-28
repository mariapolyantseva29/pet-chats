package com.pet.chats.service;

import com.pet.chats.entity.ChatEntity;
import com.pet.chats.entity.MessageEntity;
import com.pet.chats.entity.UserEntity;
import com.pet.chats.repository.ChatRepository;
import com.pet.chats.repository.MessageRepository;
import com.pet.chats.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageEntity saveMessage(int chatId, int senderId, String content) {
        ChatEntity chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found with id: " + chatId));

        UserEntity sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + senderId));

        MessageEntity message = MessageEntity.builder()
                .chat(chat)
                .sender(sender)
                .content(content)
                .build();

        return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<MessageEntity> getChatMessages(int chatId) {
        return messageRepository.findByChatIdOrderBySentAtAsc(chatId);
    }
}
