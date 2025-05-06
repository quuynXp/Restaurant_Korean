package com.connectJPA.service.kafka;

import com.connectJPA.dto.response.InvoiceResponse;
import com.connectJPA.entity.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class InvoiceKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    private final Map<String, CompletableFuture<List<Invoice>>> pendingRequests = new ConcurrentHashMap<>();

    public CompletableFuture<List<Invoice>> waitForResponse(String requestId) {
        CompletableFuture<List<Invoice>> future = new CompletableFuture<>();
        pendingRequests.put(requestId, future);
        return future;
    }

    @KafkaListener(topics = "invoice-response", groupId = "report-group")
    public void listenInvoiceResponse(InvoiceResponse response) {
        CompletableFuture<List<Invoice>> future = pendingRequests.remove(response.getRequestId());
        if (future != null) {
            future.complete(response.getInvoices());
        }
    }

    @KafkaListener(topics = "revenue-topic", groupId = "report-group")
    public void handleRevenue(Double totalRevenue) {
        System.out.println("amount 2= " + totalRevenue);
        messagingTemplate.convertAndSend("/topic/revenue", totalRevenue);
    }

}
