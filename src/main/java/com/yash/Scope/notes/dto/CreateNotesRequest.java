package com.yash.Scope.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNotesRequest {

    private String title;

    @NotBlank(message = "Notes content cannot be blank")
    private String content;

    private Long clientId;

    private Long projectId;

    public boolean isValidOwner(){
        return (clientId != null) ^ (projectId != null); // want only one owner to exist
    }

}
