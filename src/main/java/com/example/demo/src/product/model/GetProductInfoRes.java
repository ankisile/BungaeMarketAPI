package com.example.demo.src.product.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GetProductInfoRes {
    @NonNull private int productIdx;
    @NonNull private String title;
    @NonNull private Integer price;
    @NonNull private String directAddress;
    @NonNull private String productOption;
    @NonNull private String explanation;
    @NonNull private String securePayment;
    @NonNull private String sellStatus;
    @NonNull private String createdAt;
    @NonNull private String favoriteCount;
    @NonNull private String category;
    @NonNull private String productInquiry;
    @NonNull private String myFavorite;
    private List<ProductTag> productTagList;


}
