package com.yash.Scope.notes.controller;

import com.yash.Scope.notes.dto.CreateNotesRequest;
import com.yash.Scope.notes.dto.NotesResponse;
import com.yash.Scope.notes.service.NotesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;

    @PostMapping("/clients/{clientId}/notes")
    public ResponseEntity<NotesResponse> createClientNotes(
            @PathVariable Long clientId,
            @Valid @RequestBody CreateNotesRequest request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notesService.createNotes(clientId, null, request));   // pass null for projId
    }

    @PostMapping("/projects/{projectId}/notes")
    public ResponseEntity<NotesResponse> createProjectNotes(
            @PathVariable Long projectId,
            @Valid @RequestBody CreateNotesRequest request
    ){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notesService.createNotes(null, projectId, request));  // pass null for clientId
    }



}
