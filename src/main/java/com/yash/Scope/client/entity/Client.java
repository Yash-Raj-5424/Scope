package com.yash.Scope.client.entity;

import com.yash.Scope.client.enums.ContactMethod;
import com.yash.Scope.client.enums.Status;
import com.yash.Scope.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client extends BaseEntity{

    @Column(nullable = false)
    private String name;

    private String company;

    @Column(nullable = false)
    private String email;

    private String phone;
    private String website;
    private String address;
    private String timezone;

    @Enumerated(EnumType.STRING)
    private ContactMethod preferredContactMethod;

    @Enumerated(EnumType.STRING)
    private Status status;
}
