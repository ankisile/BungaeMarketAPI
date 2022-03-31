package com.example.demo.src.address.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GetDirectAddressRes {
    @NotNull
    private String directAddress;
    @NotNull
    private String main;
}
