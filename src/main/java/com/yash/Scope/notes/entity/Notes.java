package com.yash.Scope.notes.entity;


import com.yash.Scope.client.entity.Client;
import com.yash.Scope.common.entity.BaseEntity;
import com.yash.Scope.project.entity.Project;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notes extends BaseEntity {

    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Client client;

    @ManyToOne
    @JoinColumn
    private Project project;

    private boolean pinned = false;


}
