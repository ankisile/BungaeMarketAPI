package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ReviewImg {
    @NotNull
    private int reviewImgId;
    @NotNull
    private String reviewImgUrl;
}