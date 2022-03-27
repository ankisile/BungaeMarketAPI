package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexInteger;

@RestController
@RequestMapping("/app/orders")
public class OrderController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrderService orderService;
    private final OrderProvider orderProvider;
    private final JwtService jwtService;

    @Autowired
    public OrderController(OrderService orderService, OrderProvider orderProvider, JwtService jwtService) {
        this.orderService = orderService;
        this.orderProvider = orderProvider;
        this.jwtService = jwtService;
    }

    /**
     * 주문하기 API
     * [POST] /orders
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> postOrders(@RequestBody PostOrderReq postOrderReq) {

        try {
            // jwt 에서 userId 추출.
            int userIdByJwt = jwtService.getUserIdx();

            orderService.createOrder(postOrderReq, userIdByJwt);

            return new BaseResponse<>("success");
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}