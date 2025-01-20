package com.kimulegen.forohub.controller;

import com.kimulegen.forohub.domain.user.User;
import com.kimulegen.forohub.domain.user.UserDTO;
import com.kimulegen.forohub.infra.security.JWTTokenDTO;
import com.kimulegen.forohub.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity userAuthentication(@RequestBody @Valid UserDTO userDTO) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password());
        var authUser =authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((User) authUser.getPrincipal());

        return ResponseEntity.ok(new JWTTokenDTO(token));
    }
}
