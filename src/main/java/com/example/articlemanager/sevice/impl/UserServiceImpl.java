package com.example.articlemanager.sevice.impl;

import com.example.articlemanager.mapper.UserMapper;
import com.example.articlemanager.pojo.User;
import com.example.articlemanager.sevice.UserService;
import com.example.articlemanager.utils.BcryptUtil;
import com.example.articlemanager.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u =userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {

//      String md5Sting = Md5Util.getMD5String(password);
        String pwd = BcryptUtil.encode(password);
        userMapper.add(username,pwd);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }


    @Override
    public void updatePwd(String newPwd) {
        //需要根據用戶ID修改密碼
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
//        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
        userMapper.updatePwd(BcryptUtil.encode(newPwd),id);
    }
}
