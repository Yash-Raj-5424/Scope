package com.yash.Scope.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateNotesRequest {

    private String title;

    @NotBlank(message = "Notes content cannot be blank")
    @Size(max = 10000, message = "Notes content cannot exceed 10000 characters")
    private String content;


}
