package com.yash.Scope.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateNotesRequest {

    private String title;

    @NotBlank(message = "content should not be blank")
    private String content;

}
