package com.kimulegen.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public record RegisterUserDTO(
        Long id,

        @NotBlank
        String name,

        @NotBlank(message = "User su email como username")
        @Email
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank(message = "Debe tenee entre 8 y 16 caracteres")
        @Pattern(regexp = "\\d{8,16}")
        String password
) {
        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getEmail() {
                return email;
        }
}
