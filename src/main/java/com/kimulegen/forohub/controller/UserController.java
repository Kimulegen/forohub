package com.kimulegen.forohub.controller;

import com.kimulegen.forohub.domain.user.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name="bearer-key")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<Page<ListUsersDTO>> listUsers(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(userRepository.findByActiveTrue(paged).map(ListUsersDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUser (@RequestBody @Valid UpdateUserDTO updateUserDTO){
        User user = userRepository.getReferenceById(updateUserDTO.id());
        user.updateUser(updateUserDTO);
        return ResponseEntity.ok(new UpdateUserDTO(user.getId(),user.getName(), user.getEmail()));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        user.deactivateUser();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity <ResponseUserDTO> registerUser(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        var userDetail = new ResponseUserDTO(user.getId(), user.getName());
        return ResponseEntity.ok(userDetail);
    }
}
