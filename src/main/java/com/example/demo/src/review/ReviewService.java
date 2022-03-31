package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Transactional(rollbackFor = BaseException.class)
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;

    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService;
    }

    public int createReview(int storeId, int userId, PostReviewReq postReviewReq)  {

            return reviewDao.createReview(storeId, userId, postReviewReq);

    }


    public void createReviewImage(int reviewId, String reviewImageUrl)  {

            reviewDao.createReviewImage(reviewId, reviewImageUrl);

    }

    public void deleteReview(int userId, int reviewId)  {

            reviewDao.deleteReviewImgByReviewId(reviewId);

            if(reviewProvider.checkReviewImgByReviewId(reviewId)==0){
                reviewDao.deleteReview(reviewId);
            }

    }


}