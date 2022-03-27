package com.example.demo.src.order;

import com.example.demo.src.order.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getUserAddress(int userId){
        String getUserAddressQuery = "select concat(address, ' ', detail_address) as address from Address where user_id = ? and address_type='DELIVERY' and main='MAIN'";
        int getUserAddressParams = userId;
        return this.jdbcTemplate.queryForObject(getUserAddressQuery, String.class, getUserAddressParams);
    }

    public int makeOrderInfo(int userId, String address, int totalPrice, PostOrderReq postOrderReq){
        String makeOrderInfoQuery = "insert into Orders(user_id, product_id, address, total_price, trading_method, pay_method, address_option) values(?,?,?,?,?,?,?)";
        Object[] makeOrderInfoParams = new Object[]{userId,postOrderReq.getProductId(),address,totalPrice, postOrderReq.getTradingMethod(), postOrderReq.getPayMethod(), postOrderReq.getAddressOption()};
        this.jdbcTemplate.update(makeOrderInfoQuery, makeOrderInfoParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public int getPoint(int userId){
        String getPointQuery = "select point from Users where user_id = ?";
        int getPointParams = userId;
        return this.jdbcTemplate.queryForObject(getPointQuery, int.class, getPointParams);
    }

    public int getProductPrice(int productId){
        String getProductPriceQuery = "select price from Products where product_id = ?";
        int getProductPriceParams = productId;
        return this.jdbcTemplate.queryForObject(getProductPriceQuery, int.class, getProductPriceParams);
    }

    public void changeReservedStatus(int productId){
        String changeStatusQuery = "update Products set sell_status = 'RESERVED' where product_id=?";
        int changeStatusParams = productId;
        this.jdbcTemplate.update(changeStatusQuery, changeStatusParams);
    }

    public void changePoint(int userId, int usedPoint){
        String changePointQuery = "update Users set point = point-? where user_id=?";
        Object[] changePointParams = new Object[]{usedPoint, userId};
        this.jdbcTemplate.update(changePointQuery, changePointParams);
    }

    public GetProductOrderRes getOrderView(int userId, int productId) {
        String getOrderViewQuery = "select point, product_title as title, price,\n" +
                "       (select product_image_url from ProductImages where Products.product_id = ProductImages.product_id limit 1) as productImg,\n" +
                "       address, name, A.phone as phone\n" +
                "from  Products, Users\n" +
                "left outer join (select concat(Address.address, ' ', detail_address) as address, name, phone, user_id from Address where address_type='DELIVERY' and main='MAIN') as  A on Users.user_id = A.user_id\n" +
                "where Users.user_id=? and product_id=?";
        Object[] getOrderViewParams = new Object[]{userId, productId};
        return this.jdbcTemplate.queryForObject(getOrderViewQuery,
                (rs, rowNum) -> new GetProductOrderRes(
                        rs.getInt("point"),
                        rs.getString("address"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getString("productImg")
                ),
                getOrderViewParams);
    }

    public Product getProduct(int productId) {
        String getAddressInfoQuery = "select product_title as title, price,\n" +
                "       (select ProductImages.product_image_url from ProductImages where ProductImages.product_id =? limit 1) as productImg from Products where product_id=? ";
        Object[] getAddressInfoParams = new Object[]{productId, productId};

        return this.jdbcTemplate.queryForObject(getAddressInfoQuery,
                (rs, rowNum) -> new Product(

                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getString("productUrl")
                ),
                getAddressInfoParams);
    }
}