package com.example.articlemanager.controller;

import com.example.articlemanager.pojo.Result;
import com.example.articlemanager.pojo.User;
import com.example.articlemanager.sevice.UserService;
import com.example.articlemanager.utils.BcryptUtil;
import com.example.articlemanager.utils.JwtUtil;
import com.example.articlemanager.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    //註冊
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

    //登入
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp="^\\S{5,16}$")String username,@Pattern(regexp="^\\S{5,16}$")String password){
        User loginUser = userService.findByUserName(username);
        if(loginUser == null){
            return Result.error("用戶名錯誤");
        }
        if(BcryptUtil.matches(password,loginUser.getPassword()))
        {
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("用戶名錯誤或密碼錯誤");
    }

    //獲取用戶相關信息
    @GetMapping("/userInfo")
    public Result<User>  getUserInfo(/*@RequestHeader(name="Authorization")String token*/){

//        String username = (String) JwtUtil.parseToken(token).get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username =(String) map.get("username");
        User user = userService.findByUserName(username);

        return Result.success(user);
    }
    //更新用戶信息
    @PutMapping("/update")
    //RequestBody可以將 request body 裡面的 Json 格式的參數 ，轉為 Spring Boot 中 自定義的 Java class
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    //更新用戶頭像


    //更新用戶密碼

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //1.校驗參數
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        //確認oldPwd,newPwd,rePwd不為空
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            System.out.println(123);
            return Result.error("缺少必要參數");
        }

        //確認原密碼是否正確
        //根據用戶名查找密碼，並和old_Pwd比對
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        //資料庫存的是加密過後資料，所以old_Pwd也需要先加密才能比對
//        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd)))
        if(!BcryptUtil.matches(oldPwd,loginUser.getPassword()))
        {
            return Result.error("原密碼錯誤");
        }
        //確認newPwd和rePwd是否一樣
        if(!newPwd.equals(rePwd)){
            return Result.error("兩次填寫的新密碼不一樣");
        }


        if(newPwd.equals(oldPwd)){
            return Result.error("新密碼不能與舊密碼相同");
        }

        userService.updatePwd(newPwd);

        return Result.success();
    }
}
