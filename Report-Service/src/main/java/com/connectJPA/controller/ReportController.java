package com.connectJPA.controller;

import com.connectJPA.dto.request.ReportRequest;
import com.connectJPA.dto.response.MonhtlyRevenueResponseDTO;
import com.connectJPA.dto.response.MontlyRevenueResponseDTO;
import com.connectJPA.entity.RevenueReport;
import com.connectJPA.grpc.ReportServiceGrpcClient;
import com.connectJPA.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    private final ReportServiceGrpcClient reportServiceGrpcClient;

    @GetMapping("/revenue")
    public RevenueReport getRevenue(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") ReportRequest reportRequest) throws Exception {
        return reportService.generateReport(reportRequest);
    }

    @GetMapping("/monthly-revenue")
    public List<MonhtlyRevenueResponseDTO> getMonthlyRevenue(@RequestParam int year) {
        return reportServiceGrpcClient.getMonthlyRevenue(year);
    }

    @GetMapping("/test-grpc")
    public ResponseEntity<?> testGrpc() {
        try {
            List<MonhtlyRevenueResponseDTO> result = reportServiceGrpcClient.getMonthlyRevenue(2025);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi khi gọi gRPC: " + e.getMessage());
        }
    }
}
