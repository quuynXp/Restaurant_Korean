package com.connectJPA.repository;

import com.connectJPA.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceReportRepository extends JpaRepository<Long, Invoice> {
}
