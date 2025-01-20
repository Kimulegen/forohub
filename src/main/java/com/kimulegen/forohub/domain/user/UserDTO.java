package com.kimulegen.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank(message = "Use su email como username")
        @Email(message = "email no válido")
        String email,

        @NotBlank(message = "Su contraseña debe tener entre 8 y 16 caracteres")
        String password
) {}
