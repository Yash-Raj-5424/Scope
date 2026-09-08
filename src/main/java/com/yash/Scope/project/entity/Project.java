package com.yash.Scope.project.entity;

import com.yash.Scope.client.entity.Client;
import com.yash.Scope.common.entity.BaseEntity;
import com.yash.Scope.project.enums.Priority;
import com.yash.Scope.project.enums.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project extends BaseEntity {

    @Column(nullable=false)
    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private Client client;

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

    private ProjectStatus status;
}
