package com.github.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
