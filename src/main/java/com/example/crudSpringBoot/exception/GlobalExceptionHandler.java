package com.example.crudSpringBoot.exception;

import com.example.crudSpringBoot.dto.ExceptionDto;
import com.example.crudSpringBoot.dto.ValidationExceptionResponseDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, HttpServletRequest request){

        ExceptionDto exceptionResponse=new ExceptionDto(LocalDateTime.now(),ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), request.getRequestURI(),HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request){

        Map<String,String> fieldErrors =new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach((error) -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDto validationExceptionResponse=new ValidationExceptionResponseDto(LocalDateTime.now(),"Validation Failed", HttpStatus.BAD_REQUEST.getReasonPhrase(), request.getRequestURI(),HttpStatus.BAD_REQUEST.value()
        ,fieldErrors);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(validationExceptionResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runTimeExceptionHandler(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericExceptionHandler(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong. Please try again later.");
    }


}
