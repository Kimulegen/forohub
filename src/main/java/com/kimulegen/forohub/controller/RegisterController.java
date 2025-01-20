package com.kimulegen.forohub.controller;

import com.kimulegen.forohub.domain.user.RegisterUserDTO;
import com.kimulegen.forohub.domain.user.ResponseUserDTO;
import com.kimulegen.forohub.domain.user.UserRepository;
import com.kimulegen.forohub.domain.user.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;


    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid RegisterUserDTO registerUserDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            RegisterUserDTO user = userService.registerUser(registerUserDTO);
            ResponseUserDTO responseUserDTO;
            responseUserDTO = new ResponseUserDTO(user.getId(), user.getName());
            URI url = uriComponentsBuilder.path("usuario/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(url).body(responseUserDTO);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
        }
    }
}