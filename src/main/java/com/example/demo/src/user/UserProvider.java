package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    public List<GetUserRes> getUsers()  {

        return userDao.getUsers();

    }

    public List<GetUserRes> getUsersByEmail(String email)  {

        return userDao.getUsersByEmail(email);

    }


    public GetUserRes getUser(int userIdx)  {

        return userDao.getUser(userIdx);

    }

    public int checkEmail(String email)  {

            return userDao.checkEmail(email);

    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {

        if (checkEmail(postLoginReq.getEmail()) == 0) {
            throw new BaseException(POST_USERS_NOT_EXISTS_EMAIL);
        }
        if (checkUserStatusByEmail(postLoginReq.getEmail()) == 1) {
            throw new BaseException(DELETED_USER);
        }


        User user = userDao.getPwd(postLoginReq);
        String password;
        try {
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if (postLoginReq.getPassword().equals(password)) {
            int userIdx = userDao.getPwd(postLoginReq).getUserIdx();
            String jwt = jwtService.createJwt(userIdx);
            return new PostLoginRes(userIdx, jwt);
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

    public int checkUserStatusByUserId(int userId) throws BaseException {
        try {
            return userDao.checkUserStatusByUserId(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkUserStatusByEmail(String email) throws BaseException {
        try {
            return userDao.checkUserStatusByEmail(email);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public GetMyPageRes getMyPage(int userIdx)  {

            return userDao.getMyPage(userIdx);

    }

    public GetMySellingProducts getMyProducts(int userIdx, String status)  {
            return userDao.getMyProducts(userIdx, status);
    }

    public int countProductByStatus(String status, int userIdx)  {

            return userDao.countProductByStatus(status, userIdx);

    }

    public List<GetMyFollowing> getFollowings(int userIdx)  {

            List<GetMyFollowing> followings = userDao.getFollowings(userIdx);
            for (GetMyFollowing following : followings) {
                if (following.getProductCount() != 0) {
                    following.setFollowingProducts(userDao.getFollowingProducts(following.getFollowingUserIdx()));
                }
            }
            return followings;




    }

    public List<GetMyFollower> getFollowers(int userIdx)  {


            return userDao.getFollowers(userIdx);

    }

    public List<GetPurchaseRes> getPurchaseList(int userIdx)  {

            return userDao.getPurchaseList(userIdx);

    }

    public List<GetSellRes> getSellList(int userIdx)  {

            return userDao.getSellList(userIdx);

    }

    public int checkOrderPurchase(int userIdx)  {

            return userDao.checkOrderPurchase(userIdx);

    }

    public int checkOrderSell(int userIdx)  {

            return userDao.checkOrderSell(userIdx);

    }
}