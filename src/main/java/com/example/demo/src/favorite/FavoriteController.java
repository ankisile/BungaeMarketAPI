package com.example.demo.src.favorite;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.GetProductInfoRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/app/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteProvider favoriteProvider;
    private final FavoriteService favoriteService;
    private final JwtService jwtService;


//    /**
//     * 하트를 눌렀을때 찜목록에 추가
//     * @param getProductInfoRes
//     * @return
//     */
//    @PostMapping("")
//    public BaseResponse<String> createFavorite(@RequestBody GetProductInfoRes getProductInfoRes) {
//        try {
//            int userIdx = jwtService.getUserIdx();
//
//            Integer productIdx = getProductInfoRes.getProductIdx();
//            favoriteService.createFavorite(productIdx,userIdx);
//
//            return new BaseResponse<>("찜 목록에 추가되었습니다.");
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
}
