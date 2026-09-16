package com.yash.Scope.invoice.mapper;

import com.yash.Scope.invoice.dto.CreateInvoiceRequest;
import com.yash.Scope.invoice.dto.InvoiceResponse;
import com.yash.Scope.invoice.dto.UpdateInvoiceRequest;
import com.yash.Scope.invoice.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    @Mapping(target="client", ignore = true)
    @Mapping(target = "project", ignore = true)
    Invoice toEntity(CreateInvoiceRequest request);

    InvoiceResponse toResponse(Invoice invoice);

    void updateInvoiceFromDto(UpdateInvoiceRequest request, @MappingTarget Invoice invoice);


}
