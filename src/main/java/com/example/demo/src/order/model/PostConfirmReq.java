package com.example.demo.src.order.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
public class PostConfirmReq {
    @NotNull(message = "orderId를 입력해주세요")
    private Integer orderId;

}