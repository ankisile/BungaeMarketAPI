package com.example.demo.src.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class GetProductByShopRes {
    @NotNull
    private int productIdx;
    @NotNull
    private String title;
    @NotNull
    private int price;
    @NotNull
    private String productImg;
    @NotNull
    private String securePayment;
    @NotNull
    private String myFavorite;
}
