package com.yash.Scope.invoice.service;

import com.yash.Scope.client.repository.ClientRepository;
import com.yash.Scope.exception.ResourceNotFoundException;
import com.yash.Scope.invoice.dto.CreateInvoiceRequest;
import com.yash.Scope.invoice.dto.InvoiceResponse;
import com.yash.Scope.invoice.entity.Invoice;
import com.yash.Scope.invoice.mapper.InvoiceMapper;
import com.yash.Scope.invoice.repository.InvoiceRepository;
import com.yash.Scope.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public InvoiceResponse createInvoice(CreateInvoiceRequest request){

        //check whether the client exists
        if(!clientRepository.existsById(request.getClientId())){
            throw new ResourceNotFoundException("Client doesn't exist for id: " + request.getClientId());
        }

        // check whether project exists
        if(request.getProjectId() != null &&
        !projectRepository.existsById(request.getProjectId())){

            throw new ResourceNotFoundException("Project doesn't exist with id: " + request.getProjectId());
        }

        // check invoice uniqueness
        if (invoiceRepository.existsByInvoiceNumber(request.getInvoiceNumber())){
            throw new IllegalArgumentException("Invoice already exists with id: " + request.getInvoiceNumber());
        }

        Invoice invoice = invoiceMapper.toEntity(request);

        //set the entity fields manually
        if(request.getClientId() != null)
            invoice.setClient(clientRepository.getReferenceById(request.getClientId()));

        if(request.getProjectId() != null)
            invoice.setProject(projectRepository.getReferenceById(request.getProjectId()));

        invoiceRepository.save(invoice);

        return invoiceMapper.toResponse(invoice);
    }
}
