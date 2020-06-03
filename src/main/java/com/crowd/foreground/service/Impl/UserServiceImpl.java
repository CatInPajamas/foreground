package com.crowd.foreground.service.Impl;

import com.crowd.foreground.dao.UserDao;
import com.crowd.foreground.entity.Address;
import com.crowd.foreground.entity.Order;
import com.crowd.foreground.entity.User;
import com.crowd.foreground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserName(String username) throws Exception {
        User user=userDao.findByUserName(username);
        return user;
    }

    @Override
    public List<Address> getAddressByUserId(Integer userid) throws Exception {
        return userDao.getAddressByUserId(userid);
    }

    @Override
    public void addAddress(Address address) throws Exception {
        userDao.addAddress(address);
    }

    @Override
    public void deleteAddressById(Integer id) throws Exception {
        userDao.deleteAddressById(id);
    }

    @Override
    public List<Integer> getUserProjectsByUserId(Integer userid) throws Exception {
        return userDao.getUserProjectsByUserId(userid);
    }

    @Override
    public void updateUsrName(String name, Integer id) throws Exception {
        userDao.updateUsrName(name,id);
    }

    @Override
    public void updateEmail(String email, Integer id) throws Exception {
        userDao.updateEmail(email,id);
    }

    @Override
    public void updatePswd(String pswd, Integer id) throws Exception {
        userDao.updatePswd(pswd,id);
    }

    @Override
    public String saveUser(User user) {
        int count=userDao.checkUser(user.getLoginacct());
        if(count==0){
            userDao.saveUser(user);
            return "success";
        }else{
            return "fail";
        }
    }

}
