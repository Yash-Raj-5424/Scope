package com.yash.Scope.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesResponse {

    private String title;
    private String content;
    private Long clientId;
    private Long projectId;

}
