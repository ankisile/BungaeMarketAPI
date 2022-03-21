package com.example.demo.src.favorite;


import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.GetProductInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteDao favoriteDao;

    public void createFavorite(int productIdx, int userIdx) throws BaseException {
        try {


            favoriteDao.createFavorite(productIdx, userIdx);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
