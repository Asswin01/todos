package com.example.todosApps.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class TodoApiException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public TodoApiException( HttpStatus status, String message) {

        this.status = status;
        this.message = message;
    }
}
