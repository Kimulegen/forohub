package com.kimulegen.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record updatedTopicDTO(
        @NotNull
        Long id,
        String title,
        String message,
        Status status,
        @NotNull
        Long user_id,
        String course,
        LocalDateTime date
) {
}
