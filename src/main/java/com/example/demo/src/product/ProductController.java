package com.example.demo.src.product;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.example.demo.common.exception.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

import javax.validation.Valid;
import javax.validation.constraints.*;


@Validated
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
     * 상품 등록 API
     * [POST] /products
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> postProducts( @RequestBody @Valid PostProductReq postProductReq) {

        // jwt 에서 userId 추출.
        int userIdByJwt = jwtService.getUserIdx();

        if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
            throw new UnavailableUserException();
        }

        int productId = productService.createProduct(userIdByJwt, postProductReq);

        List<ProductImg> productImgList = postProductReq.getProductImgList();
        for(ProductImg productImg : productImgList){
            productService.createProductImage(productId, productImg.getProductImgUrl());
        }

        List<ProductTag> productTagList = postProductReq.getProductTagList();
        for(ProductTag productTag : productTagList){
            productService.createProductTag(productId, productTag.getTagName());
        }

        return new BaseResponse<>("success");

    }

    /**
     * 특정 상품 화면 - 상품정보 API
     * [GET] /products/:productId
     * @return BaseResponse<GetProductInfoRes>
     */
    @ResponseBody
    @GetMapping("/{productId}")
    public BaseResponse<GetProductInfoRes> getProductInfo(@PathVariable(required = false)
                                                              @NotBlank
                                                              @Pattern(regexp="^[0-9]*$", message="Invalid한 path parameter 입니다")
                                                                      String productId) {


        int userIdByJwt = jwtService.getUserIdx();

        if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
            throw new UnavailableUserException();
        }

        int id = Integer.parseInt(productId);
        if(productProvider.checkProductId(id) == 0){
            throw new InvalidException();
        }

        productService.updateViewCount(id);

        GetProductInfoRes getProductInfoRes = new GetProductInfoRes(id);

        getProductInfoRes.setProductInfo(productProvider.getProductInfos(userIdByJwt,id));
        getProductInfoRes.setProductTagList(productProvider.getProductTags(id));
        getProductInfoRes.setProductImgList(productProvider.getProductImages(id));
        getProductInfoRes.setStoreInfo(productProvider.getStoreInfos(id));

        int storeId=getProductInfoRes.getStoreInfo().getStoreId();
        getProductInfoRes.setSellProductList(productProvider.getSellProducts(storeId));

        int categoryId=getProductInfoRes.getProductInfo().getCategoryId();
        getProductInfoRes.setRelateProductList(productProvider.getRelateProducts(categoryId, id));
        getProductInfoRes.setReviewList(productProvider.getReviews(storeId));


        return new BaseResponse<>(getProductInfoRes);
    }

    /**
     * 특정 상품 화면 - 상품정보 API
     * [GET] /products
     * @return BaseResponse<GetProductRes>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetProductRes>> getProduct() {

        int userIdByJwt = jwtService.getUserIdx();

        if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
            throw new UnavailableUserException();
        }

        List<GetProductRes> products = productProvider.getProducts(userIdByJwt);
        return new BaseResponse<>(products);
    }

    /**
     * 특정 상품 화면 - 큰 카테고리 API
     * [GET] /products/largeCategory
     * @return BaseResponse<GetCategoryRes>
     */
    @ResponseBody
    @GetMapping("/largecategories")
    public BaseResponse<List<GetCategoryRes>> getLargeCategories() {

            int userIdByJwt = jwtService.getUserIdx();

            if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
                return new BaseResponse<>(DELETED_USER);
            }

            List<GetCategoryRes> largeCategories = productProvider.getLargeCategories();
            return new BaseResponse<>(largeCategories);
    }

    /**
     * 특정 상품 화면 - 중간 카테고리 API
     * [GET] /products/middleCategory/:largecategoryId
     * @return BaseResponse<GetCategoryRes>
     */
    @ResponseBody
    @GetMapping("/middlecategories/{largecategoryId}")
    public BaseResponse<List<GetCategoryRes>> getMiddleCategories(@PathVariable(required = false) String largecategoryId) {

            int userIdByJwt = jwtService.getUserIdx();

            if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
                return new BaseResponse<>(DELETED_USER);
            }

            int categoryId = Integer.parseInt(largecategoryId);
            List<GetCategoryRes> middleCategories = productProvider.getMiddleCategories(categoryId);
            return new BaseResponse<>(middleCategories);
    }


    /**
     * 특정 상품 화면 - 작은 카테고리 API
     * [GET] /products/smallCategory/:smallcategoryId
     * @return BaseResponse<GetCategoryRes>
     */
    @ResponseBody
    @GetMapping("/smallcategories/{middlecategoryId}")
    public BaseResponse<List<GetCategoryRes>> getSmallCategories(@PathVariable(required = false) String middlecategoryId) {

            int userIdByJwt = jwtService.getUserIdx();

            if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
                return new BaseResponse<>(DELETED_USER);
            }

            int categoryId = Integer.parseInt(middlecategoryId);
            List<GetCategoryRes> smallCategories = productProvider.getSmallCategories(categoryId);
            return new BaseResponse<>(smallCategories);
    }

    /**
     * 특정 상품 화면 - 상품 문의 조회 API
     * [GET] /products/:productId/inquiries
     * @return BaseResponse<GetCategoryRes>
     */
    @ResponseBody
    @GetMapping("/{productId}/inquiries")
    public BaseResponse<List<GetInquiryRes>> getInquiries(@PathVariable(required = false) String productId) {

            int userIdByJwt = jwtService.getUserIdx();

            if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
                return new BaseResponse<>(DELETED_USER);
            }

            int id = Integer.parseInt(productId);
            List<GetInquiryRes> inquiryResList = productProvider.getInquiries(id);
            return new BaseResponse<>(inquiryResList);
    }


    /**
     * 특정 상품 화면 - 상품 문의 API
     * [POST] /products/:productId/inquiries
     * @return BaseResponse<GetCategoryRes>
     */
    @ResponseBody
    @PostMapping("/{productId}/inquiries")
    public BaseResponse<String> postInquiry(@PathVariable("productId") String productId, @RequestBody @Valid PostInquiryReq postInquiryReq){

            int userIdByJwt = jwtService.getUserIdx();

            if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
                return new BaseResponse<>(DELETED_USER);
            }

            int id = Integer.parseInt(productId);

            productService.createInquiry(userIdByJwt, id, postInquiryReq);
            return new BaseResponse<>("success");
    }


    /**
     * 특정 상품 화면 - 상품문의 삭제 API
     * [DELETE] /products/:productId/inquiries/:inquiryId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("/{productId}/inquiries/{inquiryId}")
    public BaseResponse<String> deleteInquiry(@PathVariable("productId") String productId,@PathVariable("inquiryId") String inquiryId){

        int userIdByJwt = jwtService.getUserIdx();

        if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
            return new BaseResponse<>(DELETED_USER);
        }
        if(!isRegexInteger(productId)||!isRegexInteger(inquiryId)){
            return new BaseResponse<>(INVAILD_PATH_VARIABLE);
        }

        int pId = Integer.parseInt(productId);
        int iId = Integer.parseInt(inquiryId);

        if(productProvider.checkInquiry(userIdByJwt, iId, pId) == 0){
            return new BaseResponse<>(INVALID_INQUIRY_ID);
        }
        productService.deleteInquiry(userIdByJwt, iId, pId);
        return new BaseResponse<>("success");
    }

    /**
     * 특정상품화면 - 상품 판매 상태 변경 API
     * [PATCH] /products/:productId/sellStatus
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{productId}/sellStatus")
    public BaseResponse<String> changeSellStatus(@PathVariable("productId") String productId,
                                                 @RequestBody @Valid PatchSellReq patchSellReq)  {
        if(!isRegexInteger(productId)){
            return new BaseResponse<>(INVAILD_PATH_VARIABLE);
        }
        // jwt 에서 userId 추출
        int userIdByJwt = jwtService.getUserIdx();
        if (productProvider.checkUserStatusByUserId(userIdByJwt) == 0) {
            return new BaseResponse<>(DELETED_USER);
        }
        int id = Integer.parseInt(productId);

        if(productProvider.checkSellStatus(userIdByJwt, id) != 0)
            productService.updateSellStatus(userIdByJwt, id, patchSellReq.getStatus());
        return new BaseResponse<>("success");
    }


}