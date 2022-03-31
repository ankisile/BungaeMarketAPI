package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostProductReq {

    @NotNull(message = "상품사진을 등록해주세요")
    private List<ProductImg> productImgList;
    @NotEmpty(message = "상품명을 2글자 이상 입력해주세요")
    private String title;
    private int categoryLarge;
    private int categoryMiddle;
    private int categorySmall;
    @NotNull(message = "100원 이상 입력해주세요")
    @Min(100)
    private Integer price;
    @NotEmpty(message = "tag를 입력해주세요")
    private List<ProductTag> productTagList;
    @NotEmpty(message = "설명을 입력해주세요")
    private String explanation;
    private String shippingFee;
    @Min(1) private int quantity;
    private String productStatus;
    private String exchangePossible;
    private String securePayment;
}
