package com.deliveryhub.auth.authservice.controller;

import com.deliveryhub.auth.authservice.dto.request.RoleRequest;
import com.deliveryhub.auth.authservice.entity.Role;
import com.deliveryhub.auth.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/role")
@RequiredArgsConstructor

public class RoleController {

    private final AuthService authService;

    @PostMapping("/creat-role")
    public Role createRole(@RequestBody RoleRequest request) {
        return authService.createRole(request);
    }
}
