package com.deliveryhub.auth.authservice.controller;

import com.deliveryhub.auth.authservice.dto.request.LoginRequest;
import com.deliveryhub.auth.authservice.dto.request.RegisterRequest;
import com.deliveryhub.auth.authservice.dto.request.RoleRequest;
import com.deliveryhub.auth.authservice.entity.Role;
import com.deliveryhub.auth.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    public AuthController() {}
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest dto) {
        return dto.toString();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest dto) {
        return dto.getEmail() + " " + dto.getPassword();
    }

    public void refresh(){

    }

    public void logout(){
    }
}
