package com.yash.Scope.notes.controller;

import com.yash.Scope.notes.dto.CreateNotesRequest;
import com.yash.Scope.notes.dto.NotesResponse;
import com.yash.Scope.notes.dto.UpdateNotesRequest;
import com.yash.Scope.notes.service.NotesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/notes/{id}")
    public ResponseEntity<NotesResponse> getNotesById(@PathVariable Long id){

        return ResponseEntity.ok(notesService.getNotesById(id));
    }

    @GetMapping("/clients/{clientId}/notes")
    public ResponseEntity<List<NotesResponse>> getAllClientNotes(@PathVariable Long clientId){

        return ResponseEntity.ok(notesService.getAllNotesByClientId(clientId));
    }

    @GetMapping("/projects/{projectId}/notes")
    public ResponseEntity<List<NotesResponse>> getAllProjectNotes(@PathVariable Long projectId){

        return ResponseEntity.ok(notesService.getAllNotesByProjectId(projectId));
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NotesResponse> updateNotes(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNotesRequest updateNotesRequest){

        return ResponseEntity.ok(notesService.updateNotes(id, updateNotesRequest));
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNotesById(@PathVariable Long id){

        notesService.deleteNotesById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
