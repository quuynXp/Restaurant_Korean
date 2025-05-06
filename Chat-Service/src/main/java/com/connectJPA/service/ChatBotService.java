package com.connectJPA.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class ChatBotService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String askBot(String message) {
        RestTemplate restTemplate = new RestTemplate();

        String systemPrompt = "Bạn là một trợ lý chuyên về lĩnh vực nhà hàng. Chỉ trả lời các câu hỏi liên quan đến dịch vụ nhà hàng, món ăn, thực đơn, đồ uống, đặt bàn, đánh giá khách hàng, quản lý nhà hàng,... Không trả lời các chủ đề ngoài lĩnh vực này.";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(
                Map.of("role", "system", "content", systemPrompt),
                Map.of("role", "user", "content", message)
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> messageMap = (Map<String, Object>) choices.get(0).get("message");
            String content = (String) messageMap.get("content");

            if (content.toLowerCase().contains("tôi chỉ có thể trả lời") || content.toLowerCase().contains("ngoài lĩnh vực")) {
                return "Xin lỗi, tôi chỉ có thể trả lời các câu hỏi liên quan đến nhà hàng.";
            }

            return content;
        }
        return "Bot không phản hồi";
    }
}

