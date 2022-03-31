package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GetMySellingProducts {
    @NotNull
    private Integer productCount;
    @NotNull
    private String sellStatus;

    private List<GetMyProducts> products;

}
