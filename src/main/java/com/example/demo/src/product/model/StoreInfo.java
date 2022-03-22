package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StoreInfo{
    private String storeName;
    private Integer followerCount;
    private double starRate;
    private Integer reviewCount;
}