package com.example.demo.src.shop;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.shop.model.GetInquiryByShopRes;
import com.example.demo.src.shop.model.GetProductByShopRes;
import com.example.demo.src.shop.model.GetReviewByShopRes;
import com.example.demo.src.shop.model.GetShopRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/shop")
@RequiredArgsConstructor
public class ShopController {


    private final ShopProvider shopProvider;
    private final ShopService shopService;
    private final JwtService jwtService;

    @GetMapping("/{shopIdx}")
    public BaseResponse<GetShopRes> getShop(@PathVariable int shopIdx) {

        try {
            GetShopRes shop = shopProvider.getShop(shopIdx);
            return new BaseResponse<>(shop);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/{shopIdx}/products")
    public BaseResponse<List<GetProductByShopRes>> getProducts(@PathVariable int shopIdx) {
        try {

            int userIdx = jwtService.getUserIdx();

            List<GetProductByShopRes> products = shopProvider.getProducts(shopIdx, userIdx);
            return new BaseResponse<>(products);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/{shopIdx}/inquiries")
    public BaseResponse<List<GetInquiryByShopRes>> getInquiries(@PathVariable int shopIdx) {
        try {


            List<GetInquiryByShopRes> products = shopProvider.getInquiries(shopIdx);
            return new BaseResponse<>(products);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/{shopIdx}/reviews")
    public BaseResponse<List<GetReviewByShopRes>> getReviews(@PathVariable int shopIdx) {
        try {


            List<GetReviewByShopRes> reviews = shopProvider.getReviews(shopIdx);
            return new BaseResponse<>(reviews);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
