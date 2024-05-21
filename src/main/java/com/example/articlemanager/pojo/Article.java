package com.example.articlemanager.pojo;

import com.example.articlemanager.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    @NotEmpty
    @Pattern(regexp="^\\S{1,10}$")
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String coverImg;
    @State
    private String state;
    @NotNull
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
