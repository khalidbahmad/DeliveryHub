package com.deliveryhub.auth.authservice.dto.responce;

public class AuthResponse {

    public record UserResponse(Long id, String email, String fullName, String role) {}
}
