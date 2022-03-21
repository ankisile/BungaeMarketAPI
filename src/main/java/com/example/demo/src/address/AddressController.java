package com.example.demo.src.address;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.address.model.PostAddressReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final JwtService jwtService;

    @PostMapping("")
    public BaseResponse<String> createAddress(@RequestBody PostAddressReq postAddressReq, @RequestParam int userIdx) {

        if (postAddressReq.getName() == null) {
            return new BaseResponse<>(POST_ADDRESS_EMPTY_NAME);
        }
        if (postAddressReq.getPhone() == null) {
            return new BaseResponse<>(POST_ADDRESS_EMPTY_PHONE);
        }
        if (postAddressReq.getAddress() == null) {
            return new BaseResponse<>(POST_ADDRESS_EMPTY_ADDRESS);
        }
        if (postAddressReq.getDetailAddress() == null) {
            return new BaseResponse<>(POST_ADDRESS_EMPTY_DETAIL_ADDRESS);
        }

        try {
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
            addressService.createAddress(postAddressReq,userIdx);

            return new BaseResponse<>("주소가 추가되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
