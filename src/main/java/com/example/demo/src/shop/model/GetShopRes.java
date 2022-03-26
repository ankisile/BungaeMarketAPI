package com.example.demo.src.shop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetShopRes {

    private String userImageUrl;
    private String shopName;
    private String userName;
    private String introduction;
    private Integer rate;
    private Integer openDay;
    private Integer productCount;
    private Integer reviewCount;
    private Integer followingCount;
    private Integer followerCount;

}
