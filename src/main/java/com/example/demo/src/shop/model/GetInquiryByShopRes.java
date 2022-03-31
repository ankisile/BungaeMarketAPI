package com.example.demo.src.shop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class GetInquiryByShopRes {
    @NotNull
    private Integer userIdx;
    @NotNull
    private String text;
    @NotNull
    private String userName;
    @NotNull
    private String userProfileUrl;
    @NotNull
    private String createdAt;

}
