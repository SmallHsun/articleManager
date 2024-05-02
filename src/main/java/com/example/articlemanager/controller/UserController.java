package com.example.articlemanager.controller;

import com.example.articlemanager.pojo.Result;
import com.example.articlemanager.pojo.User;
import com.example.articlemanager.sevice.impl.UserService;
import com.example.articlemanager.utils.JwtUtil;
import com.example.articlemanager.utils.Md5Util;
import com.example.articlemanager.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    //(^代表匹配字符串開頭,$代表匹配字符串結尾,\S 非空白字符)被驗證的字符串必須由 5 到 16 個非空白字符組成
    @PostMapping("/register")
    public Result register(@Pattern(regexp="^\\S{5,16}$")String username,@Pattern(regexp="^\\S{5,16}$")String password){
        //查詢用戶
        User u = userService.findByUserName(username);
        if(u==null){
            userService.register(username,password);
            return Result.success();
        }else{
            return Result.error("用戶名已被註冊");
        }
    }


    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp="^\\S{5,16}$")String username,@Pattern(regexp="^\\S{5,16}$")String password){
        User loginUser = userService.findByUserName(username);

        if(loginUser == null){
            return Result.error("用戶名錯誤或密碼錯誤");
        }
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("用戶名錯誤或密碼錯誤");
    }


    @GetMapping("/userInfo")
    public Result<User>  getUserInfo(/*@RequestHeader(name="Authorization")String token*/){

//        String username = (String) JwtUtil.parseToken(token).get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username =(String) map.get("username");
        User user = userService.findByUserName(username);

        return Result.success(user);
    }
}
