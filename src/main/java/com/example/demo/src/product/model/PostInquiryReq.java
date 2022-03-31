package com.example.demo.src.product.model;

import lombok.*;

import java.util.List;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostInquiryReq {
    @NotBlank(message = "message를 입력해주세요")
    @Size(max=100)
    private String text;
}
