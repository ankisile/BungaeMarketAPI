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
        String getOrderViewQuery = "select point, product_title as title, price, shipping_fee as shippingFee, \n" +
                "       (select product_image_url from ProductImages where Products.product_id = ProductImages.product_id limit 1) as productImg,\n" +
                "       address, detailAddress, name, A.phone as phone\n" +
                "from  Products, Users\n" +
                "left outer join (select Address.address as address, detail_address as detailAddress, name, phone, user_id from Address where address_type='DELIVERY' and main='MAIN') as  A on Users.user_id = A.user_id\n" +
                "where Users.user_id=? and product_id=?";
        Object[] getOrderViewParams = new Object[]{userId, productId};
        return this.jdbcTemplate.queryForObject(getOrderViewQuery,
                (rs, rowNum) -> new GetProductOrderRes(
                        rs.getInt("point"),
                        rs.getString("address"),
                        rs.getString("detailAddress"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getString("productImg"),
                        rs.getString("shippingFee")
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

    public String getSellStatus(int productId) {
        String getSellStatusQuery = "select sell_status from Products where product_id=? ";
        int getSellStatusParams =  productId;

        return this.jdbcTemplate.queryForObject(getSellStatusQuery, String.class, getSellStatusParams);
    }

//    public GetOrderDetailRes getOrderDetail(int orderId) {
//        String getOrderViewQuery = "select order_id as orderId, trading_method as tradingMethod,  O.user_id as buyer, O.total_price as totalPrice,\n" +
//                "       YEAR(O.createdAt) as year, MONTH(O.createdAt) as month, DAY(O.createdAt) as day, date_format(O.createdAt, '%h:%i:%s') as time,\n" +
//                "        product_title as title, price, (select product_image_url from ProductImages where P.product_id = ProductImages.product_id limit 1) as productImg, P.user_id as seller, P.shipping_fee as shippingFee,\n" +
//                "        A.address as address, A.detail_address as detailAddress, name,\n" +
//                "        case when A.phone is not null then A.phone else (select phone from Users where Users.user_id=A.user_id) end as buyerPhone,\n" +
//                "       (select phone from Users where P.user_id = Users.user_id) as sellerPhone\n" +
//                "from Orders O\n" +
//                "join Products P on P.product_id = O.product_id\n" +
//                "left outer join (select address, detail_address, name, phone, user_id from Address where address_type='DELIVERY' and main='MAIN') as  A on O.user_id = A.user_id\n" +
//                "where order_id =?";
//        int getOrderViewParams = orderId;
//        return this.jdbcTemplate.queryForObject(getOrderViewQuery,
//                (rs, rowNum) -> new GetOrderDetailRes(
//                        rs.getInt("point"),
//                        rs.getString("address"),
//                        rs.getString("detailAddress"),
//                        rs.getString("name"),
//                        rs.getString("phone"),
//                        rs.getInt("price"),
//                        rs.getString("title"),
//                        rs.getString("productImg"),
//                        rs.getString("shippingFee")
//                ),
//                getOrderViewParams);
//    }
}