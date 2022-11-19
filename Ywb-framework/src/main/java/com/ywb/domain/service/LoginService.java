package com.ywb.domain.service;

import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
