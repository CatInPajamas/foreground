package com.crowd.foreground.service;


import com.crowd.foreground.entity.Address;
import com.crowd.foreground.entity.Order;
import com.crowd.foreground.entity.User;

import java.util.List;

public interface UserService {

    User findByUserName(String username) throws Exception;

    List<Address> getAddressByUserId(Integer userid) throws Exception;

    void addAddress(Address address) throws Exception;

    void deleteAddressById(Integer id) throws Exception;


    void updateUsrName(String name,Integer id) throws Exception;

    void updateEmail(String email,Integer id) throws Exception;

    void updatePswd(String pswd,Integer id) throws Exception;

    String saveUser(User user);
}
