package com.example.demo.src.shop;

import com.example.demo.config.BaseException;
import com.example.demo.src.shop.model.GetInquiryByShopRes;
import com.example.demo.src.shop.model.GetProductByShopRes;
import com.example.demo.src.shop.model.GetReviewByShopRes;
import com.example.demo.src.shop.model.GetShopRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class ShopProvider {

    private final ShopDao shopDao;

    public GetShopRes getShop(int shopIdx) throws BaseException {
        try{
            return shopDao.getShop(shopIdx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductByShopRes> getProducts(int shopIdx,int userIdx) throws BaseException {
        try{
            return shopDao.getProducts(shopIdx,userIdx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetInquiryByShopRes> getInquiries(int shopIdx) throws BaseException {
        try{
            return shopDao.getInquiries(shopIdx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetReviewByShopRes> getReviews(int shopIdx) throws BaseException {
        try{
            return shopDao.getReviews(shopIdx);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
