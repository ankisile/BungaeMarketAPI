package com.example.demo.src.shop;

import com.example.demo.config.BaseException;
import com.example.demo.src.shop.model.GetShopRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class ShopProvider {

    private final ShopDao shopDao;

    public GetShopRes getShop(int userIdx) throws BaseException {
        try{
            return shopDao.getShop(userIdx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
