package com.example.demo.src.favorite;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class FavoriteDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createFavorite(int productIdx, int userIdx) {
        jdbcTemplate.update("insert into Favorites(user_id, product_id) VALUE (?,?)", userIdx, productIdx);
    }



    public int checkIsFavorite(int productIdx, int userIdx) {
        String checkIsFavoriteQuery = "select exists(select * from Favorites where product_id = ? and user_id = ? and status='SAVED')";
        return this.jdbcTemplate.queryForObject(checkIsFavoriteQuery, int.class, productIdx, userIdx);
    }

    public void deleteFavorite(int productIdx, int userIdx) {
        Object[] deleteFavoriteParams = new Object[]{productIdx, userIdx};
        jdbcTemplate.update("update Favorites set status='DELETED' where product_id=? and user_id=?", deleteFavoriteParams);
    }

    public int checkDeletedFavorite(int productIdx, int userIdx) {
        String checkIsFavoriteQuery = "select exists(select * from Favorites where product_id = ? and user_id = ? and status='DELETED')";
        return this.jdbcTemplate.queryForObject(checkIsFavoriteQuery, int.class, productIdx, userIdx);

    }

    public void recreateFavorite(int productIdx, int userIdx) {
        jdbcTemplate.update("update Favorites set status='SAVED' where product_id=? and user_id=?", userIdx, productIdx);
    }
}
