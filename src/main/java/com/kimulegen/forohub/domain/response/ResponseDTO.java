package com.kimulegen.forohub.domain.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseDTO(
        @NotBlank
        String solution,
        @NotNull
        @Valid
        Long user_id,
        @NotNull
        @Valid
        Long topic_id,
        LocalDateTime creationDate) {
}