package com.yash.Scope.project.dto;

import com.yash.Scope.project.enums.Priority;
import com.yash.Scope.project.enums.Status;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateProjectRequest {

    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @NotNull(message = "Budget cannot be NULL")
    @Positive(message = "Budget must be greater than 0")
    private Long budget;

    @NotNull(message = "start date is required")
    private LocalDate startDate;

    @NotNull(message = "deadline cannot be NULL")
    @FutureOrPresent(message = "deadline cannot be in past")
    private LocalDate deadline;

    @PastOrPresent(message = "completion cannot be in future")
    private LocalDate completionDate;

    private Priority priority;

    private Status status;
}
