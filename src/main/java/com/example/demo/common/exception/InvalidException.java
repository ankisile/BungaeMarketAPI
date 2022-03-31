package com.example.demo.common.exception;

public class InvalidException extends RuntimeException {
    private static final String MESSAGE = "Invalid한 id입니다.";
    public InvalidException () {
        super(MESSAGE);
    }
}
