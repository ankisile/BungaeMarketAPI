package com.example.demo.src.category;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.GetMenuRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryProvider categoryProvider;

    @GetMapping("/categories")
    public BaseResponse<List<GetMenuRes>> getMenu() {


            List<GetMenuRes> menu = categoryProvider.getMenu();
            return new BaseResponse<>(menu);

    }

    @GetMapping("/homecategories")
    public BaseResponse<List<GetMenuRes>> getHomeCategories() {


            List<GetMenuRes> homeMenu = categoryProvider.getHomeCategories();
            return new BaseResponse<>(homeMenu);

    }

}
