package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class GetMyProducts {
    @NotNull
    private Integer productIdx;
    @NotNull
    private String productImgUrl;
    @NotNull
    private String title;
    @NotNull
    private Integer price;
    @NotNull
    private String securePayment;
    @NotNull
    private String sellStatus;
    @NotNull
    private String createdAt;
}
