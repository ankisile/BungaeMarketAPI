package com.example.demo.src.order.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderDetailRes {

    private String productImg;
    private String title;
    private int price;

    private int orderId;
    private String tradingMethod;


    private String shippingFee;

    private String address;
    private String name;
    private String phone;
}