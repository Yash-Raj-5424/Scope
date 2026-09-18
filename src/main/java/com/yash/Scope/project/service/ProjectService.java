package com.yash.Scope.project.service;

import com.yash.Scope.client.repository.ClientRepository;
import com.yash.Scope.exception.ResourceNotFoundException;
import com.yash.Scope.project.dto.CreateProjectRequest;
import com.yash.Scope.project.dto.ProjectResponse;
import com.yash.Scope.project.dto.UpdateProjectRequest;
import com.yash.Scope.project.entity.Project;
import com.yash.Scope.project.mapper.ProjectMapper;
import com.yash.Scope.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ClientRepository clientRepository;


    @Transactional
    public ProjectResponse createProject(CreateProjectRequest request){

        Long clientId = request.getClientId();

        if(clientId != null && !clientRepository.existsById(clientId)){ // if req carries clientId, it must be existing in db
            throw new ResourceNotFoundException("Client doesn't exist with id: " + clientId);
        }

        Project project =  projectMapper.toEntity(request);

        if(clientId != null)    project.setClient(clientRepository.getReferenceById(clientId));

        projectRepository.save(project);
        return projectMapper.toResponse(project);
    }

    public ProjectResponse getProjectById(Long id){

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        return projectMapper.toResponse(project);
    }

    public List<ProjectResponse> getAllProjects(){

        List<Project> projects = projectRepository.findAll();
        ProjectResponse projectResponse;

        List<ProjectResponse> projectResponses = new ArrayList<>();
        for(Project project: projects){
            projectResponse = projectMapper.toResponse(project);
            projectResponses.add(projectResponse);
        }

        return projectResponses;
    }

    @Transactional
    public ProjectResponse updateProject(UpdateProjectRequest updateRequest, Long id){

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project Not found with id: " + id));

        projectMapper.updateProjectFromDto(updateRequest, project);
        Project savedProject = projectRepository.save(project);

        return projectMapper.toResponse(savedProject);
    }

    public void deleteProjectById(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project Not found with id: " + id));

        projectRepository.deleteById(id);
    }
}
