package com.yash.Scope.project.mapper;


import com.yash.Scope.project.dto.CreateProjectRequest;
import com.yash.Scope.project.dto.ProjectResponse;
import com.yash.Scope.project.dto.UpdateProjectRequest;
import com.yash.Scope.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "client", ignore = true)
    Project toEntity(CreateProjectRequest request);

    void updateProjectFromDto(UpdateProjectRequest updateRequest, @MappingTarget Project project);

    ProjectResponse toResponse(Project project);

}
