package com.yash.Scope.project.controller;

import com.yash.Scope.project.dto.CreateProjectRequest;
import com.yash.Scope.project.dto.ProjectResponse;
import com.yash.Scope.project.dto.UpdateProjectRequest;
import com.yash.Scope.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){

        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(){

        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id,
                                                         @Valid @RequestBody UpdateProjectRequest request){

        return ResponseEntity.ok(projectService.updateProject(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id){

        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }

}
