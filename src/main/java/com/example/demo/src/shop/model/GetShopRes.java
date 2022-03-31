package com.example.demo.src.shop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetShopRes {
    @NotNull
    private String userImageUrl;
    @NotNull
    private String shopName;
    @NotNull
    private String userName;
    @NotNull
    private String introduction;
    @NotNull
    private Integer rate;
    @NotNull
    private Integer openDay;
    @NotNull
    private Integer productCount;
    @NotNull
    private Integer reviewCount;
    @NotNull
    private Integer inquiryCount;
    @NotNull
    private Integer followingCount;
    @NotNull
    private Integer followerCount;
    private List<GetProductByShopRes> products;
    private List<GetReviewByShopRes> reviews;
    private List<GetInquiryByShopRes> inquiries;


}
