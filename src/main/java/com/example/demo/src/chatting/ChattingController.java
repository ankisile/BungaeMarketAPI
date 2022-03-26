//package com.example.demo.src.chatting;
//
//import com.example.demo.config.BaseException;
//import com.example.demo.config.BaseResponse;
//import com.example.demo.src.chatting.model.*;
//import com.example.demo.utils.JwtService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static com.example.demo.config.BaseResponseStatus.*;
//import static com.example.demo.utils.ValidationRegex.isRegexInteger;
//
//@RestController
//@RequestMapping("/app/chattings")
//public class ChattingController {
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private final ChattingProvider chattingProvider;
//    private final ChattingService chattingService;
//    private final JwtService jwtService;
//
//    @Autowired
//    public ChattingController( ChattingService chattingService,ChattingProvider chattingProvider, JwtService jwtService) {
//        this.chattingService = chattingService;
//        this.chattingProvider = chattingProvider;
//        this.jwtService = jwtService;
//    }
//
//