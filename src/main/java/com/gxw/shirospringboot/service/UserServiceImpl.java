package com.gxw.shirospringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxw.shirospringboot.entity.User;
import com.gxw.shirospringboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return userMapper.selectOne(queryWrapper);
    }
}
