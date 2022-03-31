package com.example.demo.src.address;

import com.example.demo.src.address.model.GetAddressRes;
import com.example.demo.src.address.model.PostAddressReq;
import com.example.demo.src.address.model.PostDirectAddressReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressDao addressDao;


    /**
     * 기존에 MAIN 주소가 있고 새로 만들 주소를 MAIN으로 바꾸면 원래의 MAIN은 SUB로 바뀜
     */
    public void createAddress(PostAddressReq postAddressReq, int userIdx)  {


            List<GetAddressRes> mainAddress = addressDao.getAddress(userIdx);
            if (Objects.equals(postAddressReq.getMain(), "MAIN") && !mainAddress.isEmpty()) {

                addressDao.modifyAddressMain(mainAddress.get(0).getAddressIdx());
            }
            addressDao.createAddress(postAddressReq, userIdx);


    }

    public void createDirectAddress(PostDirectAddressReq postDirectAddressReq, int userIdx)  {

            cleanDirectAddress(userIdx);
            addressDao.createDirectAddress(postDirectAddressReq, userIdx);


    }

    public void cleanDirectAddress(int userIdx)  {

            addressDao.cleanDirectAddress(userIdx);


    }
}
