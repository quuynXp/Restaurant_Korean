package com.connectJPA.service.kafka;

import com.connectJPA.dto.request.ReportRequest;
import com.connectJPA.dto.response.InvoiceResponse;
import com.connectJPA.entity.Invoices;
import com.connectJPA.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportRequestConsumerService {
    private final KafkaTemplate<String, InvoiceResponse> kafkaTemplate;
    private final InvoiceService invoiceService;

    @KafkaListener(topics = "report-request", groupId = "invoice-group")
    public void listenReportRequest(ReportRequest request) {
        List<Invoices> invoices = invoiceService.getInvoicesByDateRange(request.getStartDate(), request.getEndDate());

        InvoiceResponse response = new InvoiceResponse();
        response.setRequestId(request.getRequestId()); // Để khớp request
        response.setReportType(request.getReportType());
        response.setInvoices(invoices);

        kafkaTemplate.send("invoice-response", response);
    }
}
