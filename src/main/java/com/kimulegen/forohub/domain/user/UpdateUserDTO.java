package com.kimulegen.forohub.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        Long id,
        String name,
        String email
) {
}
