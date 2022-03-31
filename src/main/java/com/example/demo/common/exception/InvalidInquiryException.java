package com.example.demo.common.exception;

public class InvalidInquiryException extends RuntimeException {
    private static final String MESSAGE = "올바르지 않은 inquiryId 입니다.";
    public InvalidInquiryException () {
        super(MESSAGE);
    }
}
