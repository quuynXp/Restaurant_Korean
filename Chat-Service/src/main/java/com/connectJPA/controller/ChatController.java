package com.connectJPA.controller;

import com.connectJPA.dto.ChatMessage;
import com.connectJPA.dto.ChatResponse;
import com.connectJPA.service.ChatBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatBotService chatBotService;

    @PostMapping("/ask")
    public ChatResponse askAI(@RequestBody ChatMessage message) {
        String reply = chatBotService.askBot(message.getContent());
        return new ChatResponse(reply);
    }
}
