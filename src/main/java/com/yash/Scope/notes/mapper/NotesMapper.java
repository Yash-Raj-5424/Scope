package com.yash.Scope.notes.mapper;

import com.yash.Scope.notes.dto.CreateNotesRequest;
import com.yash.Scope.notes.dto.NotesResponse;
import com.yash.Scope.notes.entity.Notes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotesMapper {

    Notes toEntity(CreateNotesRequest request);
    NotesResponse toResponse(Notes notes);
}
