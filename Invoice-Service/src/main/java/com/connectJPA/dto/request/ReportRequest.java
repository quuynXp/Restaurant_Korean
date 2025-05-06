package com.connectJPA.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {
    private String requestId;
    private String reportType;
    private LocalDate startDate;
    private LocalDate endDate;
}
