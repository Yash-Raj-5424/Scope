package com.yash.Scope.client.dto;

import com.yash.Scope.client.enums.ContactMethod;
import com.yash.Scope.client.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String company;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String phone;
    private String website;

    private String address;

    private String timezone;

    private ContactMethod preferredContactMethod;

    private Status status;
}
