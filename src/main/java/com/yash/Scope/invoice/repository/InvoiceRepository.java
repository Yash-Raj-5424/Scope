package com.yash.Scope.invoice.repository;

import com.yash.Scope.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    boolean existsByInvoiceNumber(String invoiceNumber);

}
