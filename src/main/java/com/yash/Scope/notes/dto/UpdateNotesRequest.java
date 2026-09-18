package com.yash.Scope.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNotesRequest {

    private String title;

    @NotBlank(message = "content should not be blank")
    @Size(max = 10000, message = "Notes content cannot exceed 10000 characters")
    private String content;

}
