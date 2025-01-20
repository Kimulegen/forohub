package com.kimulegen.forohub.domain.response;

import java.time.LocalDateTime;

public record ListResponsesDTO(Long id,
                               String solution,
                               Long user_id,
                               Long topic_id,
                               LocalDateTime creationDate) {
    public ListResponsesDTO(Response response
    ){
        this(response.getId(),
                response.getSolution(),
                response.getAuthor().getId(),
                response.getTopic().getId(),
                response.getCreationDate());
    }
}
