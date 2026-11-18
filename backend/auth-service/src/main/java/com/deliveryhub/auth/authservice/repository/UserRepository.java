package com.deliveryhub.auth.authservice.repository;

import com.deliveryhub.auth.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User create(User user);
}
