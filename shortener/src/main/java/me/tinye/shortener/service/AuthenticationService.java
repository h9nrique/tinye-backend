package me.tinye.shortener.service;

import me.tinye.shortener.DTO.RegisterDTO;
import me.tinye.shortener.entity.User;
import me.tinye.shortener.entity.UserRole;
import me.tinye.shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public void register(RegisterDTO body) {
        UserDetails user = userRepository.findByEmail(body.getEmail());

        if(user != null) {
            throw new Error("Email já cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(body.getPassword());
        User newUser = User.builder()
                .email(body.getEmail())
                .password(encryptedPassword)
                .name(body.getName())
                .role(UserRole.USER)
                .deleted(false)
                .build();

        userRepository.saveAndFlush(newUser);
    }
}
