package com.example.demo.src.follow.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostFollowReq {
    @NotEmpty
    private Integer storeId;
}