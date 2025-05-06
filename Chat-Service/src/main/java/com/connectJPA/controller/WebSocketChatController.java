package com.connectJPA.controller;

import com.connectJPA.dto.ChatMessage;
import com.connectJPA.service.ChatBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatBotService chatBotService;

    @MessageMapping("/chat")
    public void processMessage(ChatMessage message) {
        String response = chatBotService.askBot(message.getContent());
        messagingTemplate.convertAndSend("/topic/messages", new ChatMessage("bot", response));
    }
}
