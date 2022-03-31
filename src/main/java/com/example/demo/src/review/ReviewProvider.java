package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ReviewProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final JwtService jwtService;

    @Autowired
    public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.jwtService = jwtService;
    }

    public int checkUserStatusByUserId(int userId)  {

            return reviewDao.checkUserStatusByUserId(userId);

    }

    public int checkProductId(int productId)  {

            return reviewDao.checkProductId(productId);

    }

    public int checkExistReview(int userId, int productId)  {

            return reviewDao.checkExistReview(userId, productId);

    }

    public int getStoreId( int productId)  {

            return reviewDao.getStoreId(productId);

    }


    public int checkReviewId(int userId)  {

            return reviewDao.checkReviewId(userId);

    }




    public int checkReviewIdByReviewId(int userId, int reviewId)  {

            return reviewDao.checkReviewIdByReviewId(userId, reviewId);

    }


    public int checkReviewImgByReviewId(int reviewId)  {

            return reviewDao.checkReviewImgByReviewId(reviewId);

    }




}