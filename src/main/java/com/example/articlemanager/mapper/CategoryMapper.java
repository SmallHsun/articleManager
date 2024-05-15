package com.example.articlemanager.mapper;


import com.example.articlemanager.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("Insert into category(category_name,category_alias,create_userid,create_time,update_time)"+
            "values (#{categoryName},#{categoryAlias},#{createUserId},#{createTime},#{updateTime})")
    void add(Category category);


    @Select("select * from category where create_userid=#{userId}")
    List<Category> getList(Integer userId);

    @Select("select * from category where category_name=#{categoryName}")
    Category findByName(String categoryName);
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
    @Delete("delete from category where id=#{id}")
    void delete(Integer id);
}
