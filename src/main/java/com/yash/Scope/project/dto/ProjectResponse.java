package com.yash.Scope.project.dto;

import com.yash.Scope.project.enums.Priority;
import com.yash.Scope.project.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {

    private String name;
    private String description;
    private Long budget;
    private LocalDate startDate;
    private LocalDate deadline;
    private LocalDate completionDate;
    private Priority priority;
    private Status status;
}
