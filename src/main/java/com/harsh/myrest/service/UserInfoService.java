package com.harsh.myrest.service;

import com.harsh.myrest.entity.Department;
import com.harsh.myrest.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    UserInfo saveUser(UserInfo user);

    List<UserInfo> findAllUser();
}
