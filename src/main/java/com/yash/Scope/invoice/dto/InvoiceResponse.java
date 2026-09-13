package com.yash.Scope.invoice.dto;

import com.yash.Scope.invoice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {

    private Long id;
    private String invoiceNumber;
    private BigDecimal amount;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Status status;
    private Long clientId;
    private Long projectId;
    private LocalDateTime createdAt;

}
