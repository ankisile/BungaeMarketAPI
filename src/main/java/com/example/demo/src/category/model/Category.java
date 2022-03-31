package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class Category {
    @NotNull
    private Integer categoryLargeIdx;
    @NotNull
    private String categoryLargeName;
    @NotNull
    private String iconImageUrl;
}
