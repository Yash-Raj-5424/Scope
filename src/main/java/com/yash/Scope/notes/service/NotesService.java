package com.yash.Scope.notes.service;

import com.yash.Scope.notes.dto.CreateNotesRequest;
import com.yash.Scope.notes.dto.NotesResponse;
import com.yash.Scope.notes.entity.Notes;
import com.yash.Scope.notes.mapper.NotesMapper;
import com.yash.Scope.notes.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;
    private final NotesMapper notesMapper;

    public NotesResponse createNotes(CreateNotesRequest request){

        // validate owner
        if(!request.isValidOwner()){
            throw new IllegalArgumentException("Only exactly one owner must be provided - client/project");
        }

        Notes notes = notesMapper.toEntity(request);
        notesRepository.save(notes);

        return notesMapper.toResponse(notes);

    }

}
