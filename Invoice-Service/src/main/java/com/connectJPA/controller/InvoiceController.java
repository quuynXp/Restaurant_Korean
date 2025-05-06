package com.connectJPA.controller;

import com.connectJPA.dto.request.InvoiceRequest;
import com.connectJPA.dto.response.MonthlyRevenueDTO;
import com.connectJPA.dto.response.WeeklyRevenueDTO;
import com.connectJPA.entity.Invoices;
import com.connectJPA.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Invoices> getInvoiceByOrderId(@PathVariable Long orderId) {
        Invoices invoice = invoiceService.getInvoiceByOrderId(orderId);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoices> createInvoice1(@RequestBody InvoiceRequest invoice) {
        Invoices newInvoice = invoiceService.createInvoice1(invoice.getOrderId(), invoice.getTotalAmount());
        return ResponseEntity.ok(newInvoice);
    }

    @GetMapping("/weekly-revenue")
    public List<WeeklyRevenueDTO> getWeeklyRevenue() {
        return invoiceService.getWeeklyRevenue();
    }

    // API: Lấy tổng doanh thu của tuần hiện tại
    @GetMapping("/weekly-total")
    public Double getWeeklyTotalRevenue() {
        return invoiceService.getTotalRevenueWeekly();
    }

    @GetMapping("/monthly-revenue")
    public List<MonthlyRevenueDTO> getMonthlyTotalRevenue( @RequestParam int year) {
        return invoiceService.getMonthlyRevenue(year);
    }
}
