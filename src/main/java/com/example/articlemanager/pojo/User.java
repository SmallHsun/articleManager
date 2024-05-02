package com.example.articlemanager.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class User {
    private Integer id;
    private String username;
    @JsonIgnore//把當前對象轉為json字符串時，忽略有註解的值
    private String password;
    private String nickname;
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
