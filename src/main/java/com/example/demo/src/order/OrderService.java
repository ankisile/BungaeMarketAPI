package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.order.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Transactional(rollbackFor = BaseException.class)
public class OrderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrderDao orderDao;
    private final OrderProvider orderProvider;
    private final JwtService jwtService;

    @Autowired
    public OrderService(OrderDao orderDao, OrderProvider orderProvider, JwtService jwtService) {
        this.orderDao = orderDao;
        this.orderProvider = orderProvider;
        this.jwtService = jwtService;
    }

    public int createOrder(PostOrderReq postOrderReq, int userId) throws BaseException {
        try {

            String address = null;
            if((postOrderReq.getTradingMethod()).equals("DELIVERY")) {
                 address = orderProvider.getUserAddress(userId);
            }

            int point = postOrderReq.getPoint();
            int price = orderProvider.getProductPrice(postOrderReq.getProductId());

            int totalPrice = price-point+postOrderReq.getTax();



            int orderId = orderDao.makeOrderInfo(userId, address, totalPrice, postOrderReq);

            orderDao.changeReservedStatus(postOrderReq.getProductId());

            orderDao.changePoint(userId, point);

            return orderId;


        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}