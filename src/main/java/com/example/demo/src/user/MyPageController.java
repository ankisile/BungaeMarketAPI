package com.example.demo.src.user;


import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/mypages")
@RequiredArgsConstructor
public class MyPageController {

    private final UserProvider userProvider;
    private final JwtService jwtService;


    @GetMapping("")
    public BaseResponse<GetMyPageRes> getMyPage() {


            int userIdx = jwtService.getUserIdx();

            GetMyPageRes myPage = userProvider.getMyPage(userIdx);
            return new BaseResponse<>(myPage);

    }

    @GetMapping("/products")
    public BaseResponse<GetMySellingProducts> getMyProducts( @RequestParam String status) {

            int userIdx = jwtService.getUserIdx();
            int countProducts = userProvider.countProductByStatus(status, userIdx);
            if (countProducts == 0) {
                if (Objects.equals(status, "SELLING")) {
                    return new BaseResponse<>(NOT_EXIST_SELLING_PRODUCT);

                } else if (Objects.equals(status, "RESERVED")) {
                    return new BaseResponse<>(NOT_EXIST_RESERVED_PRODUCT);
                } else {
                    return new BaseResponse<>(NOT_EXIST_SOLDOUT_PRODUCT);
                }
            }

            GetMySellingProducts myPage = userProvider.getMyProducts(userIdx, status);
            return new BaseResponse<>(myPage);

    }

    @GetMapping("/followers")
    public BaseResponse<List<GetMyFollower>> getFollowers() {

            int userIdx = jwtService.getUserIdx();

            List<GetMyFollower> followers = userProvider.getFollowers(userIdx);
            return new BaseResponse<>(followers);


    }

    @GetMapping("/followings")
    public BaseResponse<List<GetMyFollowing>> getFollowings() {


            int userIdx = jwtService.getUserIdx();

            List<GetMyFollowing> followings = userProvider.getFollowings(userIdx);
            return new BaseResponse<>(followings);

    }

    @GetMapping("/orders-purchase")
    public BaseResponse<List<GetPurchaseRes>> getPurchaseList() {

            int userIdx = jwtService.getUserIdx();
            if (userProvider.checkOrderPurchase(userIdx) == 0) {
                return new BaseResponse<>(NOT_EXIST_ORDER_PURCHASE);
            }
            List<GetPurchaseRes> purchaseList = userProvider.getPurchaseList(userIdx);
            return new BaseResponse<>(purchaseList);

    }

    @GetMapping("/orders-sell")
    public BaseResponse<List<GetSellRes>> getSellList() {

            int userIdx = jwtService.getUserIdx();
            if (userProvider.checkOrderSell(userIdx) == 0) {
                return new BaseResponse<>(NOT_EXIST_ORDER_SELL);
            }
            List<GetSellRes> sellList = userProvider.getSellList(userIdx);
            return new BaseResponse<>(sellList);


    }
}
