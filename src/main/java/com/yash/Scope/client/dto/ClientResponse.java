package com.yash.Scope.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {

    private Long id;
    private String name;
    private String company;
    private String email;
    private String phone;
    private String website;
    private String address;
    private String timezone;
    private String preferredContactMethod;
    private String status;
}
