package com.yash.Scope.client.mapper;

import com.yash.Scope.client.dto.CreateClientRequest;
import com.yash.Scope.client.dto.ClientResponse;
import com.yash.Scope.client.dto.UpdateClientRequest;
import com.yash.Scope.client.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(CreateClientRequest request);
    void updateClientFromDto(UpdateClientRequest request, @MappingTarget Client client);
    ClientResponse toResponse(Client client);

}
