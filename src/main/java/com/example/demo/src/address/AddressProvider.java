package com.example.demo.src.address;


import com.example.demo.src.address.model.GetDirectAddressRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressProvider {

    private final AddressDao addressDao;
    public List<GetDirectAddressRes> getDirectAddresses(int userIdx)  {

        return addressDao.getDirectAddresses(userIdx);

    }

    public String getMainDirectAddress(int userId)  {

            return addressDao.getMainDirectAddress(userId);

    }

    public int checkUserStatusByUserId(int userId)  {

            return addressDao.checkUserStatusByUserId(userId);

    }
}
