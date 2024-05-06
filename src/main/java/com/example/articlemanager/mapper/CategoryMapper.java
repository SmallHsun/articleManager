package com.example.articlemanager.mapper;


import com.example.articlemanager.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("Insert into category(category_name,category_alias,create_userid,create_time,update_time)"+
            "values (#{categoryName},#{categoryAlias},#{createUserId},#{createTime},#{updateTime})")
    void add(Category category);


    @Select("select * from category where create_userid=#{userId}")
    List<Category> getList(Integer userId);
}
