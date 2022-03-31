package com.example.demo.src.recent.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GetRecentRes {
    @NotNull
    private Integer productIdx;
    @NotNull
    private String productImg;
    @NotNull
    private String title;
    @NotNull
    private Integer price;
    @NotNull
    private String sellStatus;
    @NotNull
    private String securePayment;
    @NotNull
    private String createdAt;
}
