package com.example.demo.src.favorite;


import com.example.demo.config.BaseResponse;
import com.example.demo.src.favorite.model.GetFavoriteRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/app/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteProvider favoriteProvider;
    private final FavoriteService favoriteService;
    private final JwtService jwtService;


    @GetMapping("")
    public BaseResponse<List<GetFavoriteRes>> getFavorites() {

            int userIdx = jwtService.getUserIdx();
            List<GetFavoriteRes> favorites = favoriteProvider.getFavorites(userIdx);

            return new BaseResponse<>(favorites);

    }


    /**
     * 하트를 눌렀을때 찜목록에 추가
     *
     */
    @PostMapping("/{productIdx}")
    public BaseResponse<String> exchangeFavorite(@Positive @PathVariable int productIdx) {


            int userIdx = jwtService.getUserIdx();

            String result = favoriteService.exchangeFavorite(productIdx, userIdx);

            return new BaseResponse<>(result);

    }


}
