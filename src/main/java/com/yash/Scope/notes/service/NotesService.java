package com.yash.Scope.notes.service;

import com.yash.Scope.client.repository.ClientRepository;
import com.yash.Scope.exception.ResourceNotFoundException;
import com.yash.Scope.notes.dto.CreateNotesRequest;
import com.yash.Scope.notes.dto.NotesResponse;
import com.yash.Scope.notes.entity.Notes;
import com.yash.Scope.notes.mapper.NotesMapper;
import com.yash.Scope.notes.repository.NotesRepository;
import com.yash.Scope.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;
    private final NotesMapper notesMapper;

    @Transactional
    public NotesResponse createNotes(Long clientId, Long projectId, CreateNotesRequest request){

        // ensure notes has only one owner

        boolean onlyOneOwner = (clientId != null) ^ (projectId != null);    // even tho controller ensures only one owner
        // passed, it is good to check here in case of direct service call/tests

        if(!onlyOneOwner){
            throw new IllegalArgumentException("Only one owner is allowed - client/project");
        }

        // validate whether owner exists
        if(clientId != null && !clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("Client doesn't exist with id: " + clientId);
        }
        if(projectId != null && !projectRepository.existsById(projectId)){
            throw new ResourceNotFoundException("Project doesn't exist with id: " + projectId);
        }

        Notes notes = notesMapper.toEntity(request);    // mapper only sets the non-entity fields

        //set the entity fields manually
        if(clientId != null)    notes.setClient(clientRepository.getReferenceById(clientId));
        if(projectId != null)   notes.setProject(projectRepository.getReferenceById(projectId));

        notesRepository.save(notes);

        return notesMapper.toResponse(notes);
    }

}
