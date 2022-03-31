package com.example.demo.src.address.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostDirectAddressReq {
    @NotEmpty(message = "직거래 장소를 입력해주세요")
    private String directAddress;

}
