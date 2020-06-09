package com.crowd.foreground.dao;

import com.crowd.foreground.entity.Address;
import com.crowd.foreground.entity.Order;
import com.crowd.foreground.entity.User;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.ibatis.annotations.*;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select *from t_user where loginacct=#{loginacct}")
    User findByUserName(String loginacct) throws Exception;

    @Select("select * from t_user_address where userid=#{userid}")
    List<Address> getAddressByUserId(Integer userid) throws Exception;

    @Insert("INSERT INTO `t_user_address`(`userid`, `tel`, `address`, `realname`) VALUES (#{userid}, #{tel}, #{address}, #{realname})")
    void addAddress(Address address) throws Exception;

    @Delete("delete from t_user_address where userid=${id}")
    void deleteAddressByUserId(Integer id) throws Exception;

    @Delete("delete from t_user_address where id=${id}")
    void deleteAddressById(Integer id) throws Exception;

    @Select("select itemid from t_user_project where userid=#{userid}")
    List<Integer> getUserProjectsByUserId(Integer userid) throws Exception;

    @Select("selcet userpswd from t_user where id=#{id}")
    String getPswdByUserId(Integer id);

    @Update("update t_user set username=#{name} where id=#{id}")
    void updateUsrName(String name,Integer id) throws Exception;

    @Update("update t_user set email=#{email} where id=#{id}")
    void updateEmail(String email,Integer id) throws Exception;

    @Update("update t_user set userpswd=#{pswd} where id=#{id}")
    void updatePswd(String pswd,Integer id) throws Exception;

    @Insert("INSERT INTO `t_user`(`loginacct`, `userpswd`, `username`, `email`, `createtime`) VALUES (#{loginacct},#{userpswd},#{username},#{email},#{createtime})")
    void saveUser(User user);

    @Select("select count(id) from t_user where loginacct=#{loginacct}")
    int checkUser(String loginacct);

    @Delete("delete from t_user where id=#{id}")
    void deleteUser(Integer id);
}
