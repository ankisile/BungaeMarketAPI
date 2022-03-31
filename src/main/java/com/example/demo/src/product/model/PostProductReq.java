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

    @NotNull private List<ProductImg> productImgList;
    @NotEmpty private String title;
    private int categoryLarge;
    private int categoryMiddle;
    private int categorySmall;
    @Min(100) private Integer price;
    @NotEmpty private List<ProductTag> productTagList;
    @NotEmpty private String explanation;
    private String shippingFee;
    @Min(1) private int quantity;
    private String productStatus;
    private String exchangePossible;
    private String securePayment;
}
