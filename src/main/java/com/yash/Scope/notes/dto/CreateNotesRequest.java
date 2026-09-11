package com.yash.Scope.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNotesRequest {

    private String title;

    @NotBlank(message = "Notes content cannot be blank")
    private String content;


}
