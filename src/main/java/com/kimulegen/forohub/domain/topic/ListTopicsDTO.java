package com.kimulegen.forohub.domain.topic;

import java.time.LocalDateTime;

public record ListTopicsDTO(
        Long id,
        String title,
        String message,
        Status status,
        Long user_id,
        String course,
        LocalDateTime date
) {
    public ListTopicsDTO (Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getAuthor().getId(),
                topic.getCourse(),
                topic.getDate());
    }
}