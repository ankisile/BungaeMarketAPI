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
                        rs.getString("password"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from Users where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from Users where user_id = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
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

    public String getMainAddress(int userId) {
        String getMainAddressQuery = "select address from Address where user_id = ? and main='MAIN'";
        int getMainAddressParams = userId;
        return this.jdbcTemplate.queryForObject(getMainAddressQuery, String.class, getMainAddressParams);
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
}
