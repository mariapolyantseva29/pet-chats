package com.pet.chats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatWebController {
    @GetMapping("/chats/{chatId}")
    public String chatPage(
            @PathVariable Integer chatId,
            @RequestParam Integer userId,
            Model model
    ) {
        model.addAttribute("chatId", chatId);
        model.addAttribute("userId", userId);

        return "temp-chat";
    }
}
