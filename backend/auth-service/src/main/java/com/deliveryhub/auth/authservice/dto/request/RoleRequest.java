package com.deliveryhub.auth.authservice.dto.request;
import com.deliveryhub.auth.authservice.entity.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    private RoleName roleName;
}
