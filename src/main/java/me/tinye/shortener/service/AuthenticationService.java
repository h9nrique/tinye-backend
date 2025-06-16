package me.tinye.shortener.service;

import me.tinye.shortener.DTO.LoginDTO;
import me.tinye.shortener.DTO.LoginResponseDTO;
import me.tinye.shortener.DTO.RegisterDTO;
import me.tinye.shortener.entity.User;
import me.tinye.shortener.entity.UserRole;
import me.tinye.shortener.exceptions.EmailAlreadyRegisteredException;
import me.tinye.shortener.exceptions.InvalidLoginException;
import me.tinye.shortener.exceptions.UnauthorizedException;
import me.tinye.shortener.infra.security.TokenService;
import me.tinye.shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO register(RegisterDTO body) {
        UserDetails user = userRepository.findByEmail(body.getEmail());

        if(user != null) {
            throw new EmailAlreadyRegisteredException();
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

        var usernamePassword = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

    public LoginResponseDTO login(LoginDTO body) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            var auth = authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((User) auth.getPrincipal());

            return new LoginResponseDTO(token);

        } catch (BadCredentialsException exception) {
            throw new InvalidLoginException();
        }
    }
}
