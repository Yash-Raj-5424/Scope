package com.yash.Scope.client.service;

import com.yash.Scope.client.dto.CreateClientRequest;
import com.yash.Scope.client.dto.ClientResponse;
import com.yash.Scope.client.dto.UpdateClientRequest;
import com.yash.Scope.client.entity.Client;
import com.yash.Scope.client.mapper.ClientMapper;
import com.yash.Scope.client.repository.ClientRepository;
import com.yash.Scope.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResponse createClient(CreateClientRequest request){

        Client client = clientMapper.toEntity(request);
        clientRepository.save(client);

        return clientMapper.toResponse(client);
    }

    public List<ClientResponse> getAllClients(){

        List<Client> clients =  clientRepository.findAll();

        List<ClientResponse> responses = new ArrayList<>();
        for(Client client: clients){
            ClientResponse clientResponse = clientMapper.toResponse(client);
            responses.add(clientResponse);
        }
        return responses;
    }

    public ClientResponse getClientById(Long id){

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

        return clientMapper.toResponse(client);
    }

    @Transactional
    public ClientResponse updateClient(Long id, UpdateClientRequest request){

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));


        // if req has email, and it's not client's prev email then we must check if it's duplicate
        if (request.getEmail() != null && !request.getEmail().equalsIgnoreCase(client.getEmail())) {

            boolean emailExists = clientRepository.existsByEmail(request.getEmail());
            if (emailExists) throw new IllegalArgumentException("Email already exists");
        }

        clientMapper.updateClientFromDto(request, client);
        Client savedClient = clientRepository.save(client);

        return clientMapper.toResponse(savedClient);
    }

    public void deleteClientById(Long id){
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

}
