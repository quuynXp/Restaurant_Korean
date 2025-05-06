package com.connectJPA.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private String requestId;
    private String reportType;
    private List<Invoices> invoices;
}
