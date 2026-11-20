package com.deliveryhub.auth.authservice.dto.request;

import com.deliveryhub.auth.authservice.entity.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    private String email;
    private String fullName;
    private String password;
    private RoleName role;
}
