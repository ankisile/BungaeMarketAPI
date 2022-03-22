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

    public int checkUserStatusByUserId(int userId) throws BaseException {
        try {
            return productDao.checkUserStatusByUserId(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public int checkProductId(int productId) throws BaseException {
        try {
            return productDao.checkProductId(productId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public ProductInfo getProductInfos(int userId, int productId) throws BaseException {
        try{
            ProductInfo productInfo = productDao.getProductInfos(userId, productId);
            return productInfo;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<ProductImg> getProductImages(int productId) throws BaseException {
        try {
            return productDao.getProductImages(productId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<ProductTag> getProductTags( int productId) throws BaseException {
        try {
            List<ProductTag> productTagList = productDao.getProductTags(productId);
            return productTagList;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<GetProductRes> getProducts(int userId) throws BaseException {
        try {
            List<GetProductRes> productResList = productDao.getProducts(userId);
            return productResList;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



    public StoreInfo getStoreInfos(int productId) throws BaseException {
        try{
            StoreInfo storeInfo = productDao.getStoreInfos(productId);
            return storeInfo;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<SellProduct> getSellProducts(int storeId) throws BaseException {
        try {
            return productDao.getSellProducts(storeId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<RelateProduct> getRelateProducts(int categoryId) throws BaseException {
        try {
            return productDao.getRelateProducts(categoryId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}