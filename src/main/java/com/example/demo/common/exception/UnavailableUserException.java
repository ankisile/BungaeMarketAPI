package com.example.demo.common.exception;

public class UnavailableUserException extends RuntimeException {
    private static final String MESSAGE = "비활성화된(탈퇴한) 유저입니다.";
    public UnavailableUserException () {
        super(MESSAGE);
    }
}
