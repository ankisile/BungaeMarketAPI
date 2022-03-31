package com.example.demo.common.advice;

import com.example.demo.config.AlreadyExistEmailException;
import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.response.ExceptionResponse;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.constraints.Email;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SQLException.class)
    public ExceptionResponse sqlException(SQLException e) {
        return new ExceptionResponse(false, 4000, "데이터베이스 연결에 실패하였습니다.");
    }

    @ExceptionHandler(SignatureException.class)
    public ExceptionResponse jwtNotExist(SignatureException e) {
        return new ExceptionResponse(false, 2003,"유효하지 않은 JWT입니다.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponse jwtException(IllegalArgumentException e) {
        return new ExceptionResponse(false, 2001,"JWT 토큰을 입력해주세요.");
    }

    @ExceptionHandler(AlreadyExistEmailException.class)
    public ExceptionResponse jwtException(AlreadyExistEmailException e) {
        return new ExceptionResponse(false, 2001, e.getMessage());
    }

    @ExceptionHandler(value = {NoSuchPaddingException.class,
            NoSuchAlgorithmException.class,
            BadPaddingException.class,
            IllegalBlockSizeException.class,
            InvalidAlgorithmParameterException.class,
            InvalidKeyException.class})
    public ExceptionResponse encryptException(Exception e) {
        return new ExceptionResponse(false, 4011,"비밀번호 암호화,복호화에 실패하였습니다.");
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity postValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("isSuccess", "false");
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }





}
