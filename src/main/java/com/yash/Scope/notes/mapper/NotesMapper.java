package com.yash.Scope.notes.mapper;

import com.yash.Scope.notes.dto.CreateNotesRequest;
import com.yash.Scope.notes.dto.NotesResponse;
import com.yash.Scope.notes.entity.Notes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotesMapper {

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "project", ignore = true)
    Notes toEntity(CreateNotesRequest request); // map entity by ignoring the entity fields

    NotesResponse toResponse(Notes notes);
}
