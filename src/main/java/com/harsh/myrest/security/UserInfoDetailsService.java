package com.harsh.myrest.security;

import com.harsh.myrest.entity.UserInfo;
import com.harsh.myrest.repository.UserInfoRepository;
import com.harsh.myrest.security.UnserInfoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UnserInfoUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not  found " + username));


    }
}
