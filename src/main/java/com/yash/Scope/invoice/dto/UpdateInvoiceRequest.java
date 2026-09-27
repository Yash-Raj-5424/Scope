package com.yash.Scope.invoice.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateInvoiceRequest {

    @Positive
    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private LocalDate dueDate;

    @AssertTrue(message = "dueDate must be on or after issueDate")
    public boolean isValidDueDate(){

        return issueDate == null ||
                dueDate == null ||
                !dueDate.isBefore(issueDate);
    }
}
