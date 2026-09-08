package com.yash.Scope.project.dto;

import com.yash.Scope.project.enums.Priority;
import com.yash.Scope.project.enums.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateProjectRequest {

    private String name;
    private String description;
    private Long budget;
    private LocalDate startDate;
    private LocalDate deadline;
    private LocalDate completionDate;
    private Priority priority;
    private Status status;
}
