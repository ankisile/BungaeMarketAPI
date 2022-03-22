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

    public List<ProductImg> getProductImages(int productId){
        String getProductImagesQuery = "select product_image_id as productImgId, product_image_url as productImgUrl from ProductImages where product_id = ? ";
        int getProductImagesParams = productId;
        return this.jdbcTemplate.query(getProductImagesQuery,
                (rs, rowNum) -> new ProductImg(
                        rs.getInt("productImgId"),
                        rs.getString("productImgUrl")),
                getProductImagesParams);
    }

    public int checkProductId(int productId) {
        String checkProductIdQuery = "select(exists(select * from Products where product_id = ?))";
        int checkProductIdParams = productId;
        return this.jdbcTemplate.queryForObject(checkProductIdQuery, int.class, checkProductIdParams);
    }


    public ProductInfo getProductInfos(int userId, int productId) {
        String getProductInfoQuery = "select product_title as title, price,\n" +
                "       (case when F.user_id=? then 'LIKE' else 'UNLIKE' end) as myFavorite,\n" +
                "         (case when address IS NOT NULL then address\n" +
                "            else '지역정보 없음' end) as directAddress,\n" +
                "       concat(\n" +
                "           case when product_status='USED' then '중고'\n" +
                "                else '새상품' end,\n" +
                "           '·',\n" +
                "           case when shipping_fee='EXCLUDE' then '배송비별도'\n" +
                "                else '배송비포함' end,\n" +
                "           '·',\n" +
                "           case when exchange_possible = 'EXCHANGEABLE' then '·교환가능' else \"\" end,\n" +
                "           (concat('총 ',quantity,'개'))\n" +
                "        ) as productOption,\n" +
                "       explanation,\n" +
                "       secure_payment as securePayment,\n" +
                "       case when sell_status = 'SELLING' then '판매중'\n" +
                "            when sell_status = 'RESERVED' then '예약중'\n" +
                "           when sell_status = 'SOLDOUT' then '판매완료'\n" +
                "           end as sellStatus,\n" +
                "        (case when timestampdiff(second , Products.createdAt, current_timestamp) <60\n" +
                "                then concat(timestampdiff(second, Products.createdAt, current_timestamp),' 초 전')\n" +
                "            when timestampdiff(minute , Products.createdAt, current_timestamp) <60\n" +
                "                then concat(timestampdiff(minute, Products.createdAt, current_timestamp),' 분 전')\n" +
                "            when timestampdiff(hour, Products.createdAt, current_timestamp) <24\n" +
                "                then concat(timestampdiff(hour, Products.createdAt, current_timestamp),' 시간 전')\n" +
                "            else concat(datediff( current_timestamp, Products.createdAt),' 일 전')\n" +
                "            end) as createdAt,\n" +
                "       (select case when fCount is null then 0 else fCount end) as favoriteCount,\n" +
                "       CS.category_small_name as category,CS.category_small_id as categoryId\n" +
                "       (select case when ICount is null then 0 else ICount end) as productInquiry\n" +
                "from Products\n" +
                "left join (select user_id, product_id, count(*) as fCount from Favorites group by product_id) as F on Products.product_id = F.product_id\n" +
                "left join CategorySmall CS on Products.category_small_id = CS.category_small_id\n" +
                "left join (select product_id, count(*) as ICount from ProductInquiry group by product_id) as PI on Products.product_id = PI.product_id\n" +
                "left join (select user_id, address from Address where address_type='DIRECT' and main='MAIN') as A on Products.user_id = A.user_id\n" +
                "where Products.product_id=?";
        Object[] getProductInfoParams = new Object[]{userId, productId};
        return this.jdbcTemplate.queryForObject(getProductInfoQuery,
                (rs, rowNum) -> new ProductInfo(
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("directAddress"),
                        rs.getString("productOption"),
                        rs.getString("explanation"),
                        rs.getString("securePayment"),
                        rs.getString("sellStatus"),
                        rs.getString("createdAt"),
                        rs.getString("favoriteCount"),
                        rs.getInt("categoryId"),
                        rs.getString("category"),
                        rs.getString("productInquiry"),
                        rs.getString("myFavorite")
                        ),
                getProductInfoParams);
    }

    public List<ProductTag> getProductTags(int productId) {
        String getProductTagQuery = "select tag_id as tagId, tag_name as tagName from Tag where product_id=?";
        int getProductTagParams = productId;
        return this.jdbcTemplate.query(getProductTagQuery,
                (rs, rowNum) -> new ProductTag(
                        rs.getInt("tagId"),
                        rs.getString("tagName")
                ),
                getProductTagParams);
    }

    public List<GetProductRes> getProducts(int userId) {
        String getProductsQuery = "select Products.product_id as productIdx, product_title as title, price,\n" +
                "       (select product_image_url from ProductImages where product_id = product_id limit 1) as productImg,\n"+
                "         (case when address IS NOT NULL then SUBSTRING_INDEX(address,\" \",-2)\n" +
                "            else '지역정보 없음' end) as directAddress,\n" +
                "       (case when F.user_id=? then 'LIKE' else 'UNLIKE' end) as myFavorite,\n" +
                "       secure_payment as securePayment,\n" +
                "        (case when timestampdiff(second , Products.createdAt, current_timestamp) <60\n" +
                "                then concat(timestampdiff(second, Products.createdAt, current_timestamp),' 초 전')\n" +
                "            when timestampdiff(minute , Products.createdAt, current_timestamp) <60\n" +
                "                then concat(timestampdiff(minute, Products.createdAt, current_timestamp),' 분 전')\n" +
                "            when timestampdiff(hour, Products.createdAt, current_timestamp) <24\n" +
                "                then concat(timestampdiff(hour, Products.createdAt, current_timestamp),' 시간 전')\n" +
                "            else concat(datediff( current_timestamp, Products.createdAt),' 일 전')\n" +
                "            end) as createdAt,\n" +
                "       (select case when fCount is null then 0 else fCount end) as favoriteCount\n" +
                "from Products\n" +
                "left join (select product_id, user_id, count(*) as fCount from Favorites group by product_id) as F on Products.product_id = F.product_id\n" +
                "left join (select user_id, address from Address where address_type='DIRECT' and main='MAIN') as A on Products.user_id = A.user_id\n" +
                "where sell_status='SELLING'";
        int getProductParams = userId;
        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("productIdx"),
                        rs.getString("productImg"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("directAddress"),
                        rs.getString("securePayment"),
                        rs.getString("myFavorite"),
                        rs.getString("createdAt"),
                        rs.getString("favoriteCount")
                ), getProductParams);
    }

    public StoreInfo getStoreInfos(int productId) {
        String getStoreInfoQuery = "select shop_name as storeName,\n" +
                "       case when fCount is not null then fCount else 0 end as followerCount,\n" +
                "       case when rate is not null then fCount else 0 end as starRate,\n" +
                "       case when rCount is not null then fCount else 0 end as reviewCount\n" +
                "from Products P\n" +
                "inner join Users U on P.user_id = U.user_id\n" +
                "left join (select following_user_id, count(*) as fCount from Following group by following_user_id) as F on U.user_id = F.following_user_id\n" +
                "left join (select store_id, ROUND(SUM(rate) / COUNT(store_id),1) as rate, count(*) as rCount from Reviews group by store_id)as R on U.user_id = R.store_id\n" +
                "where product_id=?";
        int getStoreInfoParams = productId;
        return this.jdbcTemplate.queryForObject(getStoreInfoQuery,
                (rs, rowNum) -> new StoreInfo(
                        rs.getString("storeName"),
                        rs.getInt("followerCount"),
                        rs.getDouble("starRate"),
                        rs.getInt("reviewCount")

                        ),
                getStoreInfoParams);
    }

    public List<SellProduct> getSellProducts(int storeId) {
        String getSellProductsQuery = "select  price,\n" +
                "       (select product_image_url from ProductImages where product_id = product_id limit 1) as productImgUrl\n" +
                "from Products P\n" +
                "where user_id=? limit 6";
        int getSellProductsParams = storeId;
        return this.jdbcTemplate.query(getSellProductsQuery,
                (rs, rowNum) -> new SellProduct(
                        rs.getString("productImgUrl"),
                        rs.getInt("price")


                        ),
                getSellProductsParams);
    }

    public List<RelateProduct> getRelateProducts(int categoryId) {
        String getRelateProductsQuery = "select  price,title\n" +
                "       (select product_image_url from ProductImages where product_id = product_id limit 1) as productImgUrl\n" +
                "inner join CategorySmall CS on P.category_small_id = CS.category_small_id\n" +
                "where category_small_id = ? limit 18";
        int getRelateProductsParams = categoryId;
        return this.jdbcTemplate.query(getRelateProductsQuery,
                (rs, rowNum) -> new RelateProduct(
                        rs.getString("productImgUrl"),
                        rs.getString("title"),
                        rs.getInt("price")

                ),
                getRelateProductsParams);
    }

}