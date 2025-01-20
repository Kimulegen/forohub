package com.kimulegen.forohub.infra.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, MethodArgumentNotValidException exception) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
