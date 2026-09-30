package com.yash.Scope.auth.service;

import com.yash.Scope.auth.dto.LoginRequest;
import com.yash.Scope.auth.dto.LoginResponse;
import com.yash.Scope.auth.dto.RegisterRequest;
import com.yash.Scope.auth.dto.UserResponse;
import com.yash.Scope.auth.entity.User;
import com.yash.Scope.auth.repository.UserRepository;
import com.yash.Scope.auth.security.JwtService;
import com.yash.Scope.exception.ConflictException;
import com.yash.Scope.exception.UnauthorizedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public LoginResponse register(RegisterRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
            throw new ConflictException("User already exists with username: " + request.getUsername());
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new ConflictException("User already exists with email: " + request.getEmail());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return LoginResponse.builder()
                .token(jwtService.generateToken(user.getUsername()))
                .user(toUserResponse(user))
                .build();


    }

    public LoginResponse login(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername());

        if(user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Invalid username or password");
        }

        return LoginResponse.builder()
                .token(jwtService.generateToken(user.getUsername()))
                .user(toUserResponse(user))
                .build();
    }

    public UserResponse me(String username){

        User user = userRepository.findByUsername(username);
        if(user == null)    throw new UnauthorizedException("User not found");
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
