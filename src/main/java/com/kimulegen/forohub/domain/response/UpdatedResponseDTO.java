package com.kimulegen.forohub.domain.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdatedResponseDTO(
        @NotNull Long id,
        String solution,
        @NotNull Long user_id,
        @NotNull Long topic_id,
        LocalDateTime creationDate
) {
}