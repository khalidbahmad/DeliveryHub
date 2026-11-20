package com.deliveryhub.auth.authservice.controller;

import com.deliveryhub.auth.authservice.dto.request.LoginRequest;
import com.deliveryhub.auth.authservice.dto.request.RegisterRequest;
import com.deliveryhub.auth.authservice.dto.responce.AuthResponse;
import com.deliveryhub.auth.authservice.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //@PostMapping("/register")
    //public AuthResponse register(@RequestBody RegisterRequest dto) {
    //    return authService.register(dto);
    //}

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(dto));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest dto) {
        return authService.login(dto);
    }

    public void refresh(){

    }

    public void logout(){
    }
}
