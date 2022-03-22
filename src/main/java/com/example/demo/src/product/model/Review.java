package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Review{
    private String title;
    private Integer rate;
    private String explanation;
    private String securePayment;
}