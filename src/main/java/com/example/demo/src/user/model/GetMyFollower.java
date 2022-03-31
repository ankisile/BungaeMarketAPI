package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GetMyFollower {
    @NotNull
    private Integer followerUserIdx;
    @NotNull
    private String followerShopName;
    @NotNull
    private String profileUrl;
    @NotNull
    private Integer productCount;
    @NotNull
    private Integer followerCount;

}
