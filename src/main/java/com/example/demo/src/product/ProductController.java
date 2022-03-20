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
    public ProductController(ProductService productService,ProductProvider productProvider, JwtService jwtService) {
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
    public BaseResponse<String> postProducts(@RequestBody PostProductReq postProductReq) {

        if(postProductReq.getProductImgList()==null){
            return new BaseResponse<>(DELETED_USER);
        }

        if(postProductReq.getTitle()==null){
            return new BaseResponse<>(DELETED_USER);

        }

        if(postProductReq.getProductTagList()==null){
            return new BaseResponse<>(DELETED_USER);

        }

        if(postProductReq.getPrice()==null){
            return new BaseResponse<>(DELETED_USER);

        }

        if(postProductReq.getExplanation()==null){
            return new BaseResponse<>(DELETED_USER);
        }


        try {
            // jwt 에서 userId 추출.
            int userIdByJwt = jwtService.getUserIdx();

            if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
                return new BaseResponse<>(DELETED_USER);
            }


            int productId = productService.createProduct(userIdByJwt, postProductReq);

            if(postProductReq.getProductImgList() != null){
                List<ProductImg> productImgList = postProductReq.getProductImgList();
                for(ProductImg productImg : productImgList){
                    productService.createProductImage(productId, productImg.getProductImg());
                }
            }

            if(postProductReq.getProductTagList() != null){
                List<ProductTag> productTagList = postProductReq.getProductTagList();
                for(ProductTag productTag : productTagList){
                    productService.createProductTag(productId, productTag.getTagName());
                }
            }


            return new BaseResponse<>("success");
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }




}