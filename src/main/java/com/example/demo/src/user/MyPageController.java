package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.shop.model.GetShopRes;
import com.example.demo.src.user.model.GetMyFollower;
import com.example.demo.src.user.model.GetMyPageRes;
import com.example.demo.src.user.model.GetMyProducts;
import com.example.demo.src.user.model.GetMySellingProducts;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final UserProvider userProvider;
    private final JwtService jwtService;

    @GetMapping("")
    public BaseResponse<GetMyPageRes> getMyPage() {
        try {

            int userIdx = jwtService.getUserIdx();

            GetMyPageRes myPage = userProvider.getMyPage(userIdx);
            return new BaseResponse<>(myPage);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/products")
    public BaseResponse<GetMySellingProducts> getMyProducts(@RequestParam String status) {


        try {

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

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/followers")
    public BaseResponse<List<GetMyFollower>> getFollowers() {
        try {

            int userIdx = jwtService.getUserIdx();

            List<GetMyFollower> followers = userProvider.getFollowers(userIdx);
            return new BaseResponse<>(followers);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/followings")
    public BaseResponse<List<GetMyFollowing>> getFollowings() {

        try {

            int userIdx = jwtService.getUserIdx();

            List<GetMyFollowing> followings = userProvider.getFollowings(userIdx);
            return new BaseResponse<>(followings);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
