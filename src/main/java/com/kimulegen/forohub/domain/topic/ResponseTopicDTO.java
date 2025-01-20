package com.kimulegen.forohub.domain.topic;

import java.time.LocalDateTime;

public record ResponseTopicDTO(
        Long id,
        String title,
        String message,
        Status status,
        Long user_id,
        String course,
        LocalDateTime date) {
    public ResponseTopicDTO(Topic topicID) {
        this(
                topicID.getId(),
                topicID.getTitle(),
                topicID.getMessage(),
                topicID.getStatus(),
                topicID.getAuthor().getId(),
                topicID.getCourse(),
                topicID.getDate());
    }
}