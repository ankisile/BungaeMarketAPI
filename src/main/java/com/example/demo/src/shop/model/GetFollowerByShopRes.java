package com.example.demo.src.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GetFollowerByShopRes {
    @NotNull
    private Integer userIdx;
    @NotNull
    private String userImageUrl;
    @NotNull
    private String shopName;
    @NotNull
    private Integer productCount;
    @NotNull
    private Integer followerCount;
}
