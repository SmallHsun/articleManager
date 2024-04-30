package com.example.articlemanager.sevice.impl;

import com.example.articlemanager.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
