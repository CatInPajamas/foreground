package com.crowd.foreground.dao;

import com.crowd.foreground.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select *from t_user where loginacct=#{loginacct}")
    User findByUserName(String loginacct);

}
