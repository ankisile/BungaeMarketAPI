package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.order.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class OrderProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrderDao orderDao;
    private final JwtService jwtService;

    @Autowired
    public OrderProvider(OrderDao orderDao, JwtService jwtService) {
        this.orderDao = orderDao;
        this.jwtService = jwtService;
    }


    public String getUserAddress(int userId) {

            return orderDao.getUserAddress(userId);

    }

    public int getPoint(int userId)  {

        return orderDao.getPoint(userId);

    }

    public int getProductPrice(int productId)  {

        return orderDao.getProductPrice(productId);

    }


    public GetProductOrderRes getOrderView(int userId, int productId)  {

            return orderDao.getOrderView(userId, productId);


    }

    public String getSellStatus(int productId)  {

            return orderDao.getSellStatus(productId);


    }

    public GetOrderDetailRes getOrderDetail(int orderId)  {

            return orderDao.getOrderDetail(orderId);


    }

    public String getOrderStatus(int orderId)  {

            return orderDao.getOrderStatus(orderId);


    }


}