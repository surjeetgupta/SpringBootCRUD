package com.example.crudSpringBoot.dto;

import java.time.LocalDateTime;

public class ExceptionDto {

    private LocalDateTime timestamp;
    private String message;
    private String error;
    private String path;
    private int statusCode;

    public ExceptionDto(LocalDateTime timestamp, String message, String error, String path, int statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.error = error;
        this.path = path;
        this.statusCode = statusCode;
    }




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


}
