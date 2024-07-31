package com.github.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.security.config.MyUserDetailsManager;
import com.github.security.entity.User;
import com.github.security.mapper.UserMapper;
import com.github.security.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private MyUserDetailsManager myUserDetailsManager;

    @Override
    public void createUser(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).build();
        myUserDetailsManager.createUser(userDetails);
    }
}
