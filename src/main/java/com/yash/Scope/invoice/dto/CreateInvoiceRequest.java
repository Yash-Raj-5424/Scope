package com.yash.Scope.invoice.dto;

import com.yash.Scope.invoice.enums.Status;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateInvoiceRequest {

    @NotBlank
    @Size(max = 64)
    private String invoiceNumber;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private Long clientId;

    private Long projectId;

    private Status status = Status.DRAFT; // keep default draft

    @AssertTrue(message = "dueDate must be on or after issueDate")
    private boolean isValidDueDate(){

        return issueDate == null ||
                dueDate == null ||
                !dueDate.isBefore(issueDate);
    }
}
