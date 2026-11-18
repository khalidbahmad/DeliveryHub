package com.deliveryhub.auth.authservice.service;

import com.deliveryhub.auth.authservice.dto.request.LoginRequest;
import com.deliveryhub.auth.authservice.dto.request.RegisterRequest;
import com.deliveryhub.auth.authservice.entity.User;
import com.deliveryhub.auth.authservice.repository.UserRepository;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@NoArgsConstructor
public class AuthService {
    private UserRepository  userRepository;
    public String registerService(@RequestBody RegisterRequest dto){
        User user = new User(dto);
        return userRepository.create()
    }

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

}
