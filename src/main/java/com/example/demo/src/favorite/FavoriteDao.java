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
}
