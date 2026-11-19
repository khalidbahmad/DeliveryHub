package com.deliveryhub.auth.authservice.controller;

import com.deliveryhub.auth.authservice.dto.request.RoleRequest;
import com.deliveryhub.auth.authservice.entity.Role;
import com.deliveryhub.auth.authservice.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/role")
public class RoleController {

    @PostMapping("/creat-role")
    public Role createRole(@RequestBody RoleRequest request) {
        return AuthService.createRole(request);
    }
}
