package me.tinye.shortener.controller;

import jakarta.validation.Valid;
import me.tinye.shortener.DTO.LoginDTO;
import me.tinye.shortener.DTO.LoginResponseDTO;
import me.tinye.shortener.DTO.RegisterDTO;
import me.tinye.shortener.entity.User;
import me.tinye.shortener.infra.security.TokenService;
import me.tinye.shortener.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO body) {
        authenticationService.register(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
