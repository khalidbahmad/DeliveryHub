package com.deliveryhub.auth.authservice.dto.responce;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresIn,          // en secondes
        UserResponse user
) {
    public record UserResponse(Long id, String email, String fullName, String role) {}
}