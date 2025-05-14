package me.tinye.shortener.controller;

import jakarta.validation.Valid;
import me.tinye.shortener.DTO.LoginDTO;
import me.tinye.shortener.DTO.RegisterDTO;
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

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginDTO body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        System.out.println("Usuário autenticado: " + auth.getPrincipal());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO body) {
        authenticationService.register(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
