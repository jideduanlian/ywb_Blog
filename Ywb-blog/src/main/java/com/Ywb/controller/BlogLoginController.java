package com.Ywb.controller;


import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.User;
import com.ywb.domain.enums.AppHttpCodeEnum;
import com.ywb.domain.exception.SystemException;
import com.ywb.domain.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //判断是否传入用户名
        if (!StringUtils.hasText(user.getUserName())){
            //提示必须传入用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
