package com.deliveryhub.auth.authservice.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("Email déjà utilisé : " + email);
    }
}