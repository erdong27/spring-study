package com.github.security.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.security.entity.User;
import com.github.security.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MyUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user=new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(true);
        userMapper.insert(user);

    }

    @Override
    public void updateUser(UserDetails userDetails) {
        User user=new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(userDetails.isEnabled());
        userMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, userDetails.getUsername()));
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.isEnabled(),
//                    true, //账户是否过期
//                    true, //凭证是否过期
//                    true, //账户是否被锁定
//                    null//权限列表
//            );
            List<GrantedAuthority> authorities=new ArrayList<>();
            authorities.add(()->"USER_LIST");
//            authorities.add(()->"USER_ADD");
            return  org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword())
                    .disabled(!user.isEnabled())
                    .credentialsExpired(false)
                    .accountExpired(false)
                    .authorities(authorities)
                    .roles("ADMIN")
                    .build();
        }
    }
}
