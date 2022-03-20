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

    public List<GetProductImgRes> getProductImages(int productId) throws BaseException {
        try {
            return productDao.getProductImages(productId);
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



}