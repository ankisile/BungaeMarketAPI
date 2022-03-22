package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RelateProduct{
    private String productImgUrl;
    private String title;
    private Integer price;
}
