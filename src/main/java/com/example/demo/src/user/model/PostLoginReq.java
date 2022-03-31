package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class PostLoginReq {
    @NotEmpty(message = "이메일를 입력해주세요")
    @Email
    private String email;
    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;
}
