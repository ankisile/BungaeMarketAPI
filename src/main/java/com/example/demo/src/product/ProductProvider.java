package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ProductProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;
    private final JwtService jwtService;

    @Autowired
    public ProductProvider(ProductDao productDao, JwtService jwtService) {
        this.productDao = productDao;
        this.jwtService = jwtService;
    }

    public int checkUserStatusByUserId(int userId)  {

        return productDao.checkUserStatusByUserId(userId);

    }


    public int checkProductId(int productId)  {
        return productDao.checkProductId(productId);

    }

    public ProductInfo getProductInfos(int userId, int productId)  {
        return productDao.getProductInfos(userId, productId);

    }


    public List<ProductImg> getProductImages(int productId)  {
        return productDao.getProductImages(productId);

    }

    public List<ProductTag> getProductTags( int productId)  {
        return productDao.getProductTags(productId);

    }


    public List<GetProductRes> getProducts(int userId)  {
        return productDao.getProducts(userId);

    }



    public StoreInfo getStoreInfos(int productId)  {
        return productDao.getStoreInfos(productId);

    }

    public List<SellProduct> getSellProducts(int storeId)  {
        return productDao.getSellProducts(storeId);

    }

    public List<RelateProduct> getRelateProducts(int categoryId, int productId)  {
        return productDao.getRelateProducts(categoryId, productId);
    }

    public List<Review> getReviews(int storeId)  {
        return productDao.getReviews(storeId);
    }

    public List<GetCategoryRes> getLargeCategories()  {
        return productDao.getLargeCategories();
    }

    public List<GetCategoryRes> getMiddleCategories(int categoryId)  {
        return productDao.getMiddleCategories(categoryId);
    }
    public List<GetCategoryRes> getSmallCategories(int categoryId)  {
        return productDao.getSmallCategories(categoryId);
    }

    public List<GetInquiryRes> getInquiries(int productId)  {
        return productDao.getInquiries(productId);
    }

    public String getInquiryCall(int productId, int inquiryId)  {
        return productDao.getInquiryCall(productId, inquiryId);
    }

    public int checkInquiry(int userId, int inquiryId, int productId)  {
        return productDao.checkInquiry(userId, inquiryId, productId);
    }

    public int checkSellStatus(int userId, int productId)  {
        return productDao.checkSellStatus(userId, productId);
    }

    public String getMainDirectAddress(int userId)  {
        return productDao.getMainDirectAddress(userId);
    }


}