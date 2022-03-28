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
    private String buyer;
    private String seller;
    private String shippingFee;
    private int totalPrice;

    private String address;
    private String detailAddress;
    private String name;
    private String phone;
}