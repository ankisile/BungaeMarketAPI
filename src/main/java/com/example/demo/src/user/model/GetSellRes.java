package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GetSellRes {
    @NotNull
    private Integer orderIdx;
    @NotNull
    private String productImgUrl;
    @NotNull
    private String orderStatus;
    @NotNull
    private String title;
    @NotNull
    private Integer price;
    @NotNull
    private String shopName;
    @NotNull
    private String securePayment;
    @NotNull
    private String orderDay;

}
