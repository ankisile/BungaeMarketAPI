package com.example.demo.src.shop;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.shop.model.GetShopRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/shop")
@RequiredArgsConstructor
public class ShopController {


//    private final ShopProvider shopProvider;
//    private final ShopService shopService;
//    private final JwtService jwtService;
//
//    @GetMapping("{userIdx}")
//    public BaseResponse<GetShopRes> getShop(@PathVariable int userIdx) {
//
//        try {
//            GetShopRes shop = shopProvider.getShop(userIdx);
//            return new BaseResponse<>(shop);
//
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
}
