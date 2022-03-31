package com.example.demo.src.shop;

import com.example.demo.config.BaseException;
import com.example.demo.src.shop.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class ShopProvider {

    private final ShopDao shopDao;

    public GetShopRes getShop(int userIdx,int shopIdx)  {

            List<GetProductByShopRes> products = shopDao.getProducts(shopIdx, userIdx);
            List<GetReviewByShopRes> reviews = shopDao.getReviews(shopIdx);
            List<GetInquiryByShopRes> inquiries = shopDao.getInquiries(shopIdx);
            GetShopRes shop = shopDao.getShop(shopIdx);
            shop.setProducts(products);
            shop.setReviews(reviews);
            shop.setInquiries(inquiries);
            return shop;

    }

    public List<GetProductByShopRes> getProducts(int shopIdx,int userIdx)  {

            return shopDao.getProducts(shopIdx,userIdx);

    }

    public List<GetInquiryByShopRes> getInquiries(int shopIdx)  {

            return shopDao.getInquiries(shopIdx);

    }

    public List<GetReviewByShopRes> getReviews(int shopIdx)  {

            return shopDao.getReviews(shopIdx);

    }

    public List<GetFollowingByShopRes> getFollowings(int shopIdx,int userIdx)  {

            List<GetFollowingByShopRes> followings = shopDao.getFollowings(shopIdx);
            for (GetFollowingByShopRes following : followings) {
                int followingUserIdx = following.getUserIdx();
                following.setProducts(shopDao.getProducts(followingUserIdx,userIdx));
            }
            return followings;

    }

    public List<GetFollowerByShopRes> getFollowers(int shopIdx)  {

            return shopDao.getFollowers(shopIdx);

    }

    public int checkProducts(int shopIdx)  {

            return shopDao.checkProducts(shopIdx);

    }
}
