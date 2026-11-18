package com.deliveryhub.auth.authservice.repository;

import com.deliveryhub.auth.authservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
