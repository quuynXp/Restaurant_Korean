package com.connectJPA.dto.response;

import com.connectJPA.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private String requestId;
    private String reportType;
    private List<Invoice> invoices;
}
