package com.deliveryhub.auth.authservice.service;

import com.deliveryhub.auth.authservice.dto.request.LoginRequest;
import com.deliveryhub.auth.authservice.dto.request.RegisterRequest;
import com.deliveryhub.auth.authservice.dto.request.RoleRequest;
import com.deliveryhub.auth.authservice.dto.responce.AuthResponse;
import com.deliveryhub.auth.authservice.entity.Role;
import com.deliveryhub.auth.authservice.entity.User;
import com.deliveryhub.auth.authservice.repository.UserRepository;
import com.deliveryhub.auth.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository  userRepository;
    private final RoleRepository  roleRepository;

    //private final PasswordEncoder passwordEncoder;

    public AuthResponse.UserResponse register(RegisterRequest dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        Role role = roleRepository.findByName(dto.getRole().name())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown role"));

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        // user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setPasswordHash(dto.getPassword());
        user.setRole(role);

        return toResponse(userRepository.save(user));
    }

    public AuthResponse.UserResponse login(LoginRequest dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .filter(u -> dto.getPassword().equals(u.getPasswordHash()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password invalid"));
                // .filter(u -> passwordEncoder.matches(dto.getPassword(), u.getPasswordHash()))
        return toResponse(user);
    }

    private AuthResponse.UserResponse toResponse(User u) {
        return new AuthResponse.UserResponse(u.getId(), u.getEmail(), u.getFullName(), u.getRole().getName());
    }

    public Role createRole(RoleRequest request) {
        String roleName = request.getRoleName().name();

        if (roleRepository.findByName(roleName).isPresent()) {
            throw new IllegalStateException("Role already exists: " + roleName);
        }

        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
}
