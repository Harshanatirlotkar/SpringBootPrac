package com.harsh.myrest.repository;

import com.harsh.myrest.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


    Optional<UserInfo> findByName(String username);
}
