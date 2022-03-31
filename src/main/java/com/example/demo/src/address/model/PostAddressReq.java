package com.example.demo.src.address.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class PostAddressReq {
    @NotEmpty(message = "이름을 입력해주세요")
    private String name;
    @NotEmpty(message = "전화번호를 입력해주세요")
    @Size(max=12)
    private String phone;
    @NotEmpty(message = "주소를 입력해주세요")
    private String address;
    @NotEmpty(message = "상세주소를 입력해주세요")
    private String detailAddress;
    @NotEmpty(message = "기본주소 설정을 입력해주세요")
    private String main;
}
