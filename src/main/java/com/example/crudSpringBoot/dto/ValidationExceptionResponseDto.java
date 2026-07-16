package com.example.crudSpringBoot.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionResponseDto {
    private LocalDateTime timestamp;
    private String message;
    private String error;
    private String path;
    private int statusCode;
    private Map<String, String> fieldErrors;

    public ValidationExceptionResponseDto(LocalDateTime timestamp, String message, String error, String path, int statusCode, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.message = message;
        this.error = error;
        this.path = path;
        this.statusCode = statusCode;
        this.fieldErrors = fieldErrors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
