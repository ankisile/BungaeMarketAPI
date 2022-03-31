package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderReq {
    @NotNull(message = "productId를 입력해주세요")
    private Integer productId;
    private Integer point;
    private Integer tax;
    @NotNull(message = "거래방법을 입력해주세요")
    private String tradingMethod;
    @NotNull(message = "거래수단을 입력해주세요")
    private String payMethod;
    private String addressOption;
}