package com.deliveryhub.auth.authservice.service;

import com.deliveryhub.auth.authservice.dto.request.LoginRequest;
import com.deliveryhub.auth.authservice.dto.request.RoleRequest;
import com.deliveryhub.auth.authservice.entity.Role;
import com.deliveryhub.auth.authservice.entity.User;
import com.deliveryhub.auth.authservice.repository.UserRepository;
import com.deliveryhub.auth.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository  userRepository;
    private final RoleRepository  roleRepository;

    //public void registerService(@RequestBody RegisterRequest dto){
    //    User user = new User(dto.getEmail(),dto.getFullName(), dto.getPasswordHash(), dto.getRole());
    //    return userRepository.create(user);
    //}

    public String loginService (@RequestBody LoginRequest dto){

        User user = userRepository.findByEmail(dto.getEmail());
        if (
                user.getEmail().equalsIgnoreCase(dto.getEmail()) &&
                user.getPasswordHash().equalsIgnoreCase(dto.getPassword())
        ) {
            return user.toString();
        }
        else{
            return "Email or password invalid";
        }

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
