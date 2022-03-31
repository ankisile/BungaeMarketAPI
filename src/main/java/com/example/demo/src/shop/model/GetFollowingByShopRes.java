package com.example.demo.src.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetFollowingByShopRes {
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
    private List<GetProductByShopRes> products;
}
