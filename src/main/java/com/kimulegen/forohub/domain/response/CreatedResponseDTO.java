package com.kimulegen.forohub.domain.response;

import java.time.LocalDateTime;

public record CreatedResponseDTO(
        Long id,
        String solution,
        Long user_id,
        Long topic_id,
        LocalDateTime creationDate
) {
    public CreatedResponseDTO(Response rVerified) {
        this(rVerified.getId(),rVerified.getSolution(),rVerified.getAuthor().getId(),rVerified.getTopic().getId(),rVerified.getCreationDate());
    }
}