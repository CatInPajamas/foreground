package com.crowd.foreground.service.Impl;

import com.crowd.foreground.dao.UserDao;
import com.crowd.foreground.entity.Address;
import com.crowd.foreground.entity.User;
import com.crowd.foreground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据账号名获取用户信息
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public User findByUserName(String username) throws Exception {
        User user=userDao.findByUserName(username);
        return user;
    }

    /**
     * 根据用户id获取其所有的地址信息
     * @param userid
     * @return
     * @throws Exception
     */
    @Override
    public List<Address> getAddressByUserId(Integer userid) throws Exception {
        return userDao.getAddressByUserId(userid);
    }

    /**
     * 添加新地址
     * @param address
     * @throws Exception
     */
    @Override
    public void addAddress(Address address) throws Exception {
        userDao.addAddress(address);
    }

    /**
     * 根据id删除地址
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteAddressById(Integer id) throws Exception {
        userDao.deleteAddressById(id);
    }


    /**
     * 修改用户昵称
     * @param name
     * @param id
     * @throws Exception
     */
    @Override
    public void updateUsrName(String name, Integer id) throws Exception {
        userDao.updateUsrName(name,id);
    }

    /**
     * 修改邮件
     * @param email
     * @param id
     * @throws Exception
     */
    @Override
    public void updateEmail(String email, Integer id) throws Exception {
        userDao.updateEmail(email,id);
    }

    /**
     * 修改密码
     * @param pswd
     * @param id
     * @throws Exception
     */
    @Override
    public void updatePswd(String pswd, Integer id) throws Exception {
        userDao.updatePswd(pswd,id);
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
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
