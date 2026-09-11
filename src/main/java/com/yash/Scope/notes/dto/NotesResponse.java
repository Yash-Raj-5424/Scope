package com.yash.Scope.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesResponse {

    private Long id;
    private String title;
    private String content;
    private Long clientId;
    private Long projectId;
    private LocalDateTime createdAt;
    private Boolean isPinned;

}
