package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PatchShopNameReq {
    @NotNull
    private Integer userIdx;
    @NotEmpty(message = "변경할 상점 이름을 입력해주세요")
    private String shopName;
}
