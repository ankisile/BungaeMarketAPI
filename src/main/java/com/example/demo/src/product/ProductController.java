package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

@RestController
@RequestMapping("/app/products")
public class ProductController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;
    private final ProductProvider productProvider;
    private final JwtService jwtService;

    @Autowired
    public ReviewController(roductService productService,ProductProvider productProvider, JwtService jwtService) {
        this.productService = productService;
        this.productProvider = productProvider;
        this.jwtService = jwtService;
    }

    /**
     * 리뷰 작성 API
     * [POST] /reviews
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> postReviews(@RequestBody PostReviewReq postReviewReq) {





        try {
            // jwt 에서 userId 추출.
            int userIdByJwt = jwtService.getUserId();

            if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
                return new BaseResponse<>(DELETED_USER);
            }


            if(reviewProvider.checkOrderInfoId(userIdByJwt, postReviewReq.getOrderId()) == 0){
                return new BaseResponse<>(INVALID_ORDER_INFO_ID);
            }

            if(reviewProvider.checkExistReview(userIdByJwt, postReviewReq.getOrderId()) == 1){
                return new BaseResponse<>(EXISTS_REVIEW);
            }

            int storeId = reviewProvider.getStoreId(userIdByJwt, postReviewReq.getOrderId());


            if(postReviewReq.getOrderMenu() != null) {
                List<OrderMenu> orderMenuList = postReviewReq.getOrderMenu();
                for (OrderMenu orderMenu : orderMenuList) {
                    if (reviewProvider.checkOrderMenuId(orderMenu.getOrderMenuId(), postReviewReq) == 0) {
                        return new BaseResponse<>(INVALID_ORDER_INFO_MENU_ID);

                    }
                }

            }


            int productId = reviewService.createReview(storeId, userIdByJwt, postReviewReq);

            if(postReviewReq.getOrderMenu() != null){
                List<OrderMenu> orderMenuList = postReviewReq.getOrderMenu();
                for(OrderMenu orderMenu : orderMenuList){
                    if(reviewProvider.checkOrderMenuId(orderMenu.getOrderMenuId(), postReviewReq) != 0){
                        if(orderMenu.getRecommend()==1)
                            reviewService.createRecommend(reviewId, orderMenu.getOrderMenuId(), orderMenu.getRecommendDesc());
                    }
                }

            }

            if(postReviewReq.getReviewImgList() != null){
                List<ReviewImg> reviewImgList = postReviewReq.getReviewImgList();
                for(ReviewImg reviewImg : reviewImgList){
                    reviewService.createReviewImage(reviewId, reviewImg.getReviewImg());
                }
            }

            return new BaseResponse<>("success");
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }




}