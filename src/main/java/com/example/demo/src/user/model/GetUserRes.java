package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    @NotNull
    private int userIdx;
    @NotNull
    private String userName;
    @NotNull
    private String shopName;
    @NotNull
    private String email;
    @NotNull
    private String phone;
}
