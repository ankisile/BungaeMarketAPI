package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from Users";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("shop_name"),
                        rs.getString("email"),
                        rs.getString("phone"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from Users where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("shop_name"),
                        rs.getString("email"),
                        rs.getString("phone")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from Users where user_id = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("shop_name"),
                        rs.getString("email"),
                        rs.getString("phone")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into Users (user_name,email,password,phone) VALUES (?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getEmail(), postUserReq.getPassword(), postUserReq.getPhone()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        int lastIdx = jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
        String shopName = "상점" + lastIdx + "호";
        jdbcTemplate.update("update Users set shop_name=? where user_id=?", shopName, lastIdx);
        return lastIdx;
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from Users where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    public int modifyUserInfo(PatchUserReq patchUserReq,int userIdx){
        String modifyUserNameQuery = "update Users set gender = ?,birth_date=?,phone=? where user_id = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getGender(), patchUserReq.getBirthDate(), patchUserReq.getPhone(), userIdx};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select user_id, user_name,shop_name,email,password,phone,profile_Url,point,introduction,gender,birth_date from Users where email = ?";
        String getPwdParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("shop_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("profile_Url"),
                        rs.getInt("point"),
                        rs.getString("introduction"),
                        rs.getString("gender"),
                        rs.getString("birth_date")
                ),
                getPwdParams
                );

    }




    public int checkUserStatusByUserId(int userId) {
        String checkUserStatusByUserIdQuery = "select exists(select * from Users where user_id = ? and status = 'ACTIVE')";
        int checkUserStatusByUserIdParams = userId;
        return this.jdbcTemplate.queryForObject(checkUserStatusByUserIdQuery, int.class, checkUserStatusByUserIdParams);
    }


    public int checkUserStatusByEmail(String email) {
        String checkUserStatusByEmailQuery = "select exists(select * from Users where email = ? and status = 'WITHDRAWAL')";
        String checkUserStatusByEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkUserStatusByEmailQuery, int.class, checkUserStatusByEmailParams);
    }


    public int modifyShopName(PatchShopNameReq patchShopNameReq, int userIdx) {
        String modifyUserNameQuery = "update Users set shop_name=? where user_id = ? ";
        Object[] modifyUserNameParams = new Object[]{patchShopNameReq.getShopName(), userIdx};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public GetMyPageRes getMyPage(int userIdx) {
        String getMyPageQuery = "select profile_Url, shop_name,\n" +
                "       case when rate is not null then rate else 0 end as rate,\n" +
                "       case when favoriteCount is not null then favoriteCount else 0 end as favoriteCount,\n" +
                "       case when reviewCount is not null then reviewCount else 0 end as reviewCount,\n" +
                "       case when followerCount is not null then followerCount else 0 end as follwerCount,\n" +
                "       case when followingCount is not null then followingCount else 0 end as followingCount\n" +
                "from Users\n" +
                "         left join (select user_id, count(*) as followerCount from Following group by user_id) as Follower\n" +
                "                   on Users.user_id = Follower.user_id\n" +
                "         left join (select following_user_id, count(*) as followingCount\n" +
                "                    from Following\n" +
                "                    group by following_user_id) as Following\n" +
                "                   on Users.user_id = Following.following_user_id\n" +
                "         left join (select store_id, ROUND(SUM(rate) / COUNT(store_id), 1) as rate,count(*) reviewCount from Reviews group by store_id) as R\n" +
                "                   on R.store_id = Users.user_id\n" +
                "         left join (select user_id,count(*) as favoriteCount from Favorites group by user_id) as Favorite on Favorite.user_id=Users.user_id\n" +
                "where Users.user_id = ?";
        return this.jdbcTemplate.queryForObject(getMyPageQuery,
                (rs, rowNum) -> new GetMyPageRes(
                        rs.getString("profile_Url"),
                        rs.getString("shop_name"),
                        rs.getInt("rate"),
                        rs.getInt("favoriteCount"),
                        rs.getInt("reviewCount"),
                        rs.getInt("follwerCount"),
                        rs.getInt("followingCount")),
                userIdx);

    }

    public GetMySellingProducts getMyProducts(int userIdx,String status) {
        int productCount = countProductByStatus(status, userIdx);

        List<GetMyProducts> getMyProducts = jdbcTemplate.query("select product_id,\n" +
                        "       (select product_image_url from ProductImages where ProductImages.product_id = Products.product_id limit 1) as product_image_url,\n" +
                        "       product_title,\n" +
                        "       price,\n" +
                        "       secure_payment,\n" +
                        "       sell_status,\n" +
                        "       createdAt\n" +
                        "from Products\n" +
                        "where user_id = 5 and sell_status=? and status='SAVED'",
                (rs, rowNum) -> new GetMyProducts(
                        rs.getInt("product_id"),
                        rs.getString("product_image_url"),
                        rs.getString("product_title"),
                        rs.getInt("price"),
                        rs.getString("secure_payment"),
                        rs.getString("sell_status"),
                        rs.getString("createdAt")
                ));

        return new GetMySellingProducts(productCount, getMyProducts);
    }


    public int countProductByStatus(String status, int userIdx) {

        String countProductByStatus = "select case when count(*) is not null then count(*) else 0 end as productCount\n" +
                "from Products\n" +
                "where user_id = ? and sell_status = ? and status = 'SAVED'";
        Object[] countProductByStatusParams = new Object[]{userIdx, status};

        return jdbcTemplate.queryForObject(countProductByStatus, int.class, countProductByStatusParams);
    }
}
