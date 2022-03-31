package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class FollowingProduct {
    @NotNull
    private Integer productIdx;
    @NotNull
    private String productImgUrl;
    @NotNull
    private Integer price;
}
