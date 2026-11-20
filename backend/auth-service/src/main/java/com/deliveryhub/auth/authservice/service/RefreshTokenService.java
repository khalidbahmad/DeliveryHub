package com.deliveryhub.auth.authservice.service;

import com.deliveryhub.auth.authservice.entity.RefreshToken;
import com.deliveryhub.auth.authservice.entity.User;
import com.deliveryhub.auth.authservice.exception.TokenRefreshException;
import com.deliveryhub.auth.authservice.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class RefreshTokenService {

    private static final SecureRandom RANDOM = new SecureRandom();

    private final RefreshTokenRepository repository;
    private final long expirationDays;

    public RefreshTokenService(
            RefreshTokenRepository repository,
            @Value("${app.jwt.refresh-token-expiration-days}") long expirationDays) {
        this.repository = repository;
        this.expirationDays = expirationDays;
    }

    @Transactional
    public RefreshToken create(User user) {
        RefreshToken token = RefreshToken.builder()
                .token(generateToken())
                .user(user)
                .expiresAt(LocalDateTime.now().plusDays(expirationDays))
                .revoked(false)
                .build();
        return repository.save(token);
    }

    @Transactional(noRollbackFor = TokenRefreshException.class)
    public RefreshToken rotate(String oldToken) {
        RefreshToken existing = repository.findByToken(oldToken)
                .orElseThrow(() -> new TokenRefreshException("Refresh token inconnu"));

        // Un token déjà révoqué qui revient = probable vol : on coupe toutes les sessions
        if (existing.isRevoked()) {
            repository.revokeAllByUser(existing.getUser());
            throw new TokenRefreshException("Refresh token déjà utilisé, reconnexion requise");
        }

        if (existing.isExpired()) {
            throw new TokenRefreshException("Refresh token expiré, reconnexion requise");
        }

        existing.setRevoked(true);
        repository.save(existing);

        return create(existing.getUser());
    }

    @Transactional
    public void revoke(String token) {
        repository.revokeByToken(token);
    }

    @Transactional
    public void revokeAll(User user) {
        repository.revokeAllByUser(user);
    }

    /** 256 bits aléatoires, encodés en Base64 URL-safe (43 caractères). */
    private String generateToken() {
        byte[] bytes = new byte[32];
        RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}