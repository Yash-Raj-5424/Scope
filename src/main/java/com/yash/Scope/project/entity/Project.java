package com.yash.Scope.project.entity;

import com.yash.Scope.client.entity.Client;
import com.yash.Scope.common.entity.BaseEntity;
import com.yash.Scope.project.enums.Priority;
import com.yash.Scope.project.enums.Status;
import jakarta.persistence.*;
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

    private Long budget;

    private LocalDate startDate;

    private LocalDate deadline;

    private LocalDate completionDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;
}
