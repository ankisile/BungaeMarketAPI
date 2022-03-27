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
}