package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    @NotEmpty(message = "사용자 이름을 입력해주세요")
    private String UserName;
    @NotEmpty(message = "이메일을 입력해주세요")
    @Email
    private String email;
    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;
    @NotEmpty(message = "전화번호를 입력해주세요")
    @Size(max=12)
    private String phone;

}
