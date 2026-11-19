package com.deliveryhub.auth.authservice.dto.request;

import com.deliveryhub.auth.authservice.entity.Role;
import com.deliveryhub.auth.authservice.entity.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String fullName;
    private String passwordHash;
    private Role role;

}
