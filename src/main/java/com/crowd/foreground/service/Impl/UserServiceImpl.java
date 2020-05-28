package com.crowd.foreground.service.Impl;

import com.crowd.foreground.dao.UserDao;
import com.crowd.foreground.entity.User;
import com.crowd.foreground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserName(String username) {

        User user=userDao.findByUserName(username);
        return user;
    }
}
