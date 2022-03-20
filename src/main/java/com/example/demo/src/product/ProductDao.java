package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int checkUserStatusByUserId(int userId) {
        String checkUserStatusByUserIdQuery = "select exists(select * from Users where user_id = ? and status = 'ACTIVE')";
        int checkUserStatusByUserIdParams = userId;
        return this.jdbcTemplate.queryForObject(checkUserStatusByUserIdQuery, int.class, checkUserStatusByUserIdParams);
    }



    public int createProduct(int userId, PostProductReq postProductReq){
        String createProductQuery = "insert into Products(user_id, product_title,\n" +
                "category_large_id,category_middle_id,category_small_id,\n" +
                "product_status,exchange_possible,price,shipping_fee,\n" +
                "explanation, quantity, secure_payment) \n" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] createProductParams = new Object[]{userId, postProductReq.getTitle(), postProductReq.getCategoryLarge(),
                postProductReq.getCategoryMiddle(),postProductReq.getCategorySmall(),postProductReq.getProductStatus(),
                postProductReq.getExchangePossible(),postProductReq.getPrice(),postProductReq.getShippingFee(),
                postProductReq.getExplanation(), postProductReq.getQuantity(), postProductReq.getSecurePayment()};
        this.jdbcTemplate.update(createProductQuery, createProductParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }


    public void createProductImage(int productId, String productImageUrl) {
        String createProductImageQuery = "insert into ProductImages(product_id, product_image_url) values(?,?)";
        Object[] createProductImageParams = new Object[]{productId, productImageUrl};
        this.jdbcTemplate.update(createProductImageQuery, createProductImageParams);
    }

    public void createProductTag(int productId, String tagName) {
        String createProductTagQuery = "insert into Tag(product_id, tag_name) values(?,?)";
        Object[] createProductTagParams = new Object[]{productId, tagName};
        this.jdbcTemplate.update(createProductTagQuery, createProductTagParams);
    }


}