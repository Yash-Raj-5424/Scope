package com.yash.Scope.client.repository;

import com.yash.Scope.client.entity.Client;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(@Email(message = "Invalid email format") String email);
}
