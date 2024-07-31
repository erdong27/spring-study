package com.github.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.security.entity.User;

public interface UserService extends IService<User> {

   void  createUser(User user);
}
