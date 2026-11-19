package com.deliveryhub.auth.authservice.service;

import com.deliveryhub.auth.authservice.dto.request.LoginRequest;
import com.deliveryhub.auth.authservice.dto.request.RoleRequest;
import com.deliveryhub.auth.authservice.entity.Role;
import com.deliveryhub.auth.authservice.entity.User;
import com.deliveryhub.auth.authservice.entity.enums.RoleName;
import com.deliveryhub.auth.authservice.repository.UserRepository;
import com.deliveryhub.auth.authservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@NoArgsConstructor
public class AuthService {
    private UserRepository  userRepository;
    private static RoleRepository  roleRepository;

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

    public static Role createRole(RoleRequest name) {
        Role role = new Role();
        role.setName(name.getRoleName().name());
        try {
            return roleRepository.save(role);
        } catch (Exception e) {
            throw new IllegalStateException("Role already exists: " + name, e);
        }
    }

}
