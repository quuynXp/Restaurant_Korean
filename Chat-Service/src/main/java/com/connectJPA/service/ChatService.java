package com.connectJPA.service;

import com.connectJPA.entity.ChatHistory;
import com.connectJPA.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatHistoryRepository chatHistoryRepository;


    public void logMessage(String sender, String content, String response) {
        // Phân tích nội dung nếu cần
        if (content != null && content.length() > 200) {
            log.warn("Nội dung người dùng gửi quá dài: {}", content.length());
        }

        // Ghi log thông thường
        log.info("[CHAT] {} -> {}", sender, content);

        // Lưu vào DB
        ChatHistory history = new ChatHistory();
        history.setSender(sender);
        history.setContent(content);
        history.setResponse(response);

        chatHistoryRepository.save(history);
    }
}
