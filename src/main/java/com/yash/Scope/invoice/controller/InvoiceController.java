package com.yash.Scope.invoice.controller;

import com.yash.Scope.invoice.dto.CreateInvoiceRequest;
import com.yash.Scope.invoice.dto.InvoiceResponse;
import com.yash.Scope.invoice.dto.UpdateInvoiceRequest;
import com.yash.Scope.invoice.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceResponse> createInvoice(
            @Valid @RequestBody CreateInvoiceRequest request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(invoiceService.createInvoice(request));
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponse> getInvoiceById(@PathVariable Long id){
        return ResponseEntity
                .ok(invoiceService.getInvoiceById(id));
    }

    @GetMapping("/clients/{clientId}/invoices")
    public ResponseEntity<List<InvoiceResponse>> getAllInvoiceByClientId(@PathVariable Long clientId){
        return ResponseEntity
                .ok(invoiceService.getAllInvoiceByClientId(clientId));
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponse> updateInvoice(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInvoiceRequest request
            ){

        return ResponseEntity
                .ok(invoiceService.updateInvoice(id, request));
    }

    @PatchMapping("/invoices/{id}/paid")
    public ResponseEntity<InvoiceResponse> markInvoiceAsPaid(
            @PathVariable Long id){

        return ResponseEntity.ok(invoiceService.markAsPaid(id));
    }

    @PatchMapping("/invoices/{id}/sent")
    public ResponseEntity<InvoiceResponse> markInvoiceAsSent(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(invoiceService.markAsSent(id));
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Void> deleteInvoiceById(@PathVariable Long id){
        invoiceService.deleteInvoiceById(id);
        return ResponseEntity
                .noContent().build();
    }

}
