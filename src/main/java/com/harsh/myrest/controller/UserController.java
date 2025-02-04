package com.harsh.myrest.controller;


import com.harsh.myrest.entity.Department;
import com.harsh.myrest.entity.UserInfo;
import com.harsh.myrest.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo)
    {
        UserInfo userDB = userInfoService.saveUser(userInfo);
        return new ResponseEntity<>(userDB, HttpStatus.CREATED);
    }


    @GetMapping("/welcome")
    public ResponseEntity<String> welcome()
    {
        return new ResponseEntity<>("Welcome to user api", HttpStatus.CREATED);
    }
}
