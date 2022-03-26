package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductInfo{
    private String title;
    private String price;
    private String directAddress;
    private String productOption;
    private String explanation;
    private String securePayment;
    private String sellStatus;
    private String createdAt;
    private String favoriteCount;
    private int categoryId;
    private String category;
    private String productInquiry;
    private String myFavorite;
}
