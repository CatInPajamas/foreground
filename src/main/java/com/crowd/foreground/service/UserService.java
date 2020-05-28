package com.crowd.foreground.service;


import com.crowd.foreground.entity.User;

public interface UserService {

    User findByUserName(String username);
}
