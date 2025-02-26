package com.harsh.myrest.service;

import com.harsh.myrest.entity.UserInfo;
import com.harsh.myrest.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository unseInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo saveUser(UserInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return unseInfoRepository.save(user);
    }

    @Override
    public List<UserInfo> findAllUser() {
        return unseInfoRepository.findAll();
    }
}
