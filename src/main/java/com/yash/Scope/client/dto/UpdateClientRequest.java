package com.yash.Scope.client.dto;

import com.yash.Scope.client.enums.ContactMethod;
import com.yash.Scope.client.enums.Status;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UpdateClientRequest {

    private String name;

    private String company;

    @Email(message = "Invalid email format")
    private String email;

    private String phone;
    private String website;
    private String timezone;
    private ContactMethod preferredContactMethod;
    private Status status;

}
