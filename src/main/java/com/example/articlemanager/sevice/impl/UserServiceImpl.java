package com.example.articlemanager.sevice.impl;

import com.example.articlemanager.mapper.UserMapper;
import com.example.articlemanager.pojo.User;
import com.example.articlemanager.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements com.example.articlemanager.sevice.impl.UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u =userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        String md5Sting = Md5Util.getMD5String(password);
        userMapper.add(username,md5Sting);
    }
}
