package com.example.demo.common.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.response.ExceptionResponse;
import java.sql.SQLException;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SQLException.class)
    public ExceptionResponse sqlException(SQLException e) {
        return new ExceptionResponse(false, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponse jwtException(RuntimeException e) {
        return new ExceptionResponse(false, e.hashCode(),e.getMessage());
    }

}
