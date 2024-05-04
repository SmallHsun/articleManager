package com.example.articlemanager.sevice;

import com.example.articlemanager.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);

    void updatePwd(String newPwd);
}
