package com.example.articlemanager.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import javax.swing.*;
import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = {Update.class})
    private Integer id;
    @NotEmpty
    @Pattern(regexp="^\\S{1,10}$")
    private String categoryName;
    @NotEmpty
    @Pattern(regexp="^\\S{1,10}$")
    private String categoryAlias;
    private Integer createUserId;
    //將時間格式化成yyyy-MM-dd HH:mm:ss
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public interface Add extends Default {}

    public interface Update extends Default{}
}
