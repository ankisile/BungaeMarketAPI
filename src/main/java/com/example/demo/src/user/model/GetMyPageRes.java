package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetMyPageRes {
    @NotNull
    private String userImageUrl;
    @NotNull
    private String shopName;
    @NotNull
    private Integer rate;
    @NotNull
    private Integer favoriteCount;
    @NotNull
    private Integer reviewCount;
    @NotNull
    private Integer followerCount;
    @NotNull
    private Integer followingCount;
}
