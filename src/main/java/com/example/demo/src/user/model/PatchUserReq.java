package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PatchUserReq {

    @NotEmpty(message = "성별을 입력해주세요")
    private String gender;
    @NotEmpty(message = "생년월일을 입력해주세요")
    private String  birthDate;
    @NotEmpty(message = "전화번호를 입력해주세요")
    private String phone;
}
