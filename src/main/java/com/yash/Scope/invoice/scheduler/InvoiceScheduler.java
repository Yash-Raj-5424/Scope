package com.yash.Scope.invoice.scheduler;

import com.yash.Scope.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InvoiceScheduler {

    private final InvoiceService invoiceService;

    @Scheduled(cron = "0 5 0 * * *")
    public void runOverdueProcessing(){

        int overdueMarked = invoiceService.markOverdueInvoices();
        log.info("{} invoices marked as OVERDUE" ,overdueMarked);
    }

}
