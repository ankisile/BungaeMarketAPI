package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewReq {
    private Integer productId;
    private Double reviewRate;
    @NotEmpty
    private String reviewDesc;
    @NotNull
    private List<ReviewImg> reviewImgList;

}
