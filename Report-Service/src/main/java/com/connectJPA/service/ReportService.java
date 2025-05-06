package com.connectJPA.service;

import com.connectJPA.dto.request.ReportRequest;
import com.connectJPA.entity.Invoice;
import com.connectJPA.entity.RevenueReport;
import com.connectJPA.service.kafka.InvoiceKafkaConsumer;
import com.connectJPA.service.kafka.ReportRequestProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRequestProducer reportRequestProducer;
    private final InvoiceKafkaConsumer invoiceKafkaConsumer;

    public RevenueReport generateReport(ReportRequest reportRequest) throws Exception {
        String requestId = UUID.randomUUID().toString();
        reportRequestProducer.sendReportRequest(requestId,reportRequest.getReportType(), reportRequest.getStart(), reportRequest.getEnd());

        // Wait for response (timeout 5s)
        List<Invoice> invoices = invoiceKafkaConsumer.waitForResponse(requestId)
                .get(5, TimeUnit.SECONDS);

        Double total = invoices.stream()
                .mapToDouble(invoice -> invoice.getTotalAmount()).sum();

        return new RevenueReport(reportRequest.getReportType(),reportRequest.getStart(), reportRequest.getEnd(), total, invoices.size());
    }

}
