package me.tinye.shortener.controller;

import jakarta.validation.Valid;
import me.tinye.shortener.DTO.LoginDTO;
import me.tinye.shortener.DTO.LoginResponseDTO;
import me.tinye.shortener.DTO.RegisterDTO;
import me.tinye.shortener.entity.User;
import me.tinye.shortener.exceptions.UnauthorizedException;
import me.tinye.shortener.infra.security.TokenService;
import me.tinye.shortener.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO body) {
        LoginResponseDTO loginResponse = authenticationService.login(body);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid RegisterDTO body) {
        LoginResponseDTO registerResponse = authenticationService.register(body);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }
}
