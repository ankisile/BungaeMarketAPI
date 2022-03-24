package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.shop.model.GetShopRes;
import com.example.demo.src.user.model.GetMyPageRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final UserProvider userProvider;
    private final JwtService jwtService;

    @GetMapping("")
    public BaseResponse<GetMyPageRes> getMyPage() {
        try {

            int userIdx = jwtService.getUserIdx();

            GetMyPageRes myPage = userProvider.getMyPage(userIdx);
            return new BaseResponse<>(myPage);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
