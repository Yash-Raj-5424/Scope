package com.yash.Scope.client.mapper;

import com.yash.Scope.client.dto.ClientRequest;
import com.yash.Scope.client.dto.ClientResponse;
import com.yash.Scope.client.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequest request);

    ClientResponse toResponse(Client client);

}
