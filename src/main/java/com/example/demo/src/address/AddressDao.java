package com.example.demo.src.address;

import com.example.demo.src.address.model.GetAddressRes;
import com.example.demo.src.address.model.PostAddressReq;
import com.example.demo.src.address.model.PostDirectAddressReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AddressDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createAddress(PostAddressReq postAddressReq, int userIdx) {
        Object[] createAddressParams = new Object[]{userIdx, postAddressReq.getName(), postAddressReq.getPhone(), postAddressReq.getAddress(), postAddressReq.getDetailAddress(),postAddressReq.getMain()};
        return jdbcTemplate.update("insert into Address(user_id, name, phone, address, detail_address,main) VALUE (?,?,?,?,?,?)", createAddressParams);
    }

    public List<GetAddressRes> getAddress(int userIdx) {
        return jdbcTemplate.query("select address_id,name,phone,address,detail_address,main from Address where user_id=? and main='MAIN'",
                (rs, rowNum) -> new GetAddressRes(
                        rs.getInt("address_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("detail_address"),
                        rs.getString("main")
                ), userIdx);
    }

    public void modifyAddressMain(Integer addressIdx) {
        jdbcTemplate.update("update Address set main='SUB' where address_id=?", addressIdx);
    }

    public void createDirectAddress(PostDirectAddressReq postDirectAddressReq, int userIdx) {
        Object[] createDirectAddressParams = new Object[]{userIdx, postDirectAddressReq.getDirectAddress(), "DIRECT"};
        jdbcTemplate.update("insert into Address(user_id,detail_address,address_type) VALUE ()", createDirectAddressParams);
    }
}
