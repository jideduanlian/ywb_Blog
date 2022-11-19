package com.Ywb.controller;

import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.LoginUser;
import com.ywb.domain.entity.Menu;
import com.ywb.domain.entity.User;
import com.ywb.domain.enums.AppHttpCodeEnum;
import com.ywb.domain.exception.SystemException;
import com.ywb.domain.service.LoginService;
import com.ywb.domain.service.MenuService;
import com.ywb.domain.service.RoleService;
import com.ywb.domain.utils.BeanCopyUtils;
import com.ywb.domain.utils.SecurityUtils;

import com.ywb.domain.vo.AdminUserInfoVo;
import com.ywb.domain.vo.RouterVo;
import com.ywb.domain.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }
    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getinfo(){
        //获取当前登录用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> prems =menuService.selectPremsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(prems,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);

    }
    @GetMapping("getRouters")
    public ResponseResult<RouterVo> getRouters(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //查询menu 结果返回tree类型
        List<Menu> menus = menuService.slelctRouterMenuTreeByUserId(loginUser.getUser().getId());
        //封装数据返回
        return ResponseResult.okResult(new RouterVo(menus));
    }
    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

}
