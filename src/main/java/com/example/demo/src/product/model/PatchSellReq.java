package com.example.demo.src.product.model;

import lombok.*;

import java.util.List;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchSellReq {
    @NotNull(message = "상태값을 입력해주세요")
    private String status;
}
