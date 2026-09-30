package com.yash.Scope.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.Scope.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

            http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s ->
                        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth ->
                            auth
                                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(e ->
                        e.authenticationEntryPoint(
                                (req, res, ex) ->
                                         writeError(req, res, HttpStatus.UNAUTHORIZED, "Authentication required")
                        )
                        .accessDeniedHandler(
                                (req, res, ex) ->
                                         writeError(req, res, HttpStatus.FORBIDDEN, "Access Denied")
                        )
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private void writeError(
            HttpServletRequest req,
            HttpServletResponse res,
            HttpStatus status,
            String message) throws IOException {

        res.setStatus(status.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);

        res.getWriter()
                .write(objectMapper.writeValueAsString(
                        ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(status.value())
                                .error(status.getReasonPhrase())
                                .message(message)
                                .path(req.getRequestURI())
                                .build()
                ));

    }
}
