package com.example.articlemanager.mapper;

import com.example.articlemanager.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {


    @Select("select * from user where username=#{username}")
    User findByUserName(String username);


    @Insert("insert into user(username,password,create_time,update_time)"+
    "values (#{username},#{password},now(),now())")
    void add(String username, String password);
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);
    @Update("update user set password=#{newPwd},update_time=now() where id=#{id}")
    void updatePwd(String newPwd,Integer id);
}
