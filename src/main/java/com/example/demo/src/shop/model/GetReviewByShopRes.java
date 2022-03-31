package com.example.demo.src.shop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewByShopRes {
    @NotNull
    private Integer userIdx;
    @NotNull
    private String profileUrl;
    @NotNull
    private Integer rate;
    @NotNull
    private String text;
    @NotNull
    private String productTitle;
    @NotNull
    private String createdAt;

}
