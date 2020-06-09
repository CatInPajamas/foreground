package com.crowd.foreground.service.Impl;

import com.crowd.foreground.ForegroundApplication;
import com.crowd.foreground.dao.UserDao;
import com.crowd.foreground.entity.Address;
import com.crowd.foreground.entity.User;
import com.crowd.foreground.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ForegroundApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    public void findByUserName() throws Exception{
        User user=userService.findByUserName("user");
        Assert.assertEquals("啦啦啦",user.getUsername());
    }

    @Test
    public void getAddressByUserId() throws Exception {
        List<Address> addressList=userService.getAddressByUserId(3);
        Assert.assertTrue(addressList.size()==4);

    }

    @Test
    public void addAddress() throws Exception{
        userDao.deleteAddressByUserId(21);
        Date date=new Date();
        String s="test@"+date.toString();
        Address address=new Address(21,"123",s,"test");
        userService.addAddress(address);
        List<Address> addressList=userService.getAddressByUserId(21);
        Assert.assertTrue(addressList.get(0).getAddress().equals(s));
    }

    @Test
    public void deleteAddressById() throws Exception{
        userDao.deleteAddressByUserId(21);
        Date date=new Date();
        String s="test@"+date.toString();
        Address address=new Address(21,"123",s,"test");
        userService.addAddress(address);
        List<Address> addressList=userService.getAddressByUserId(21);
        userService.deleteAddressById(addressList.get(0).getId());
        List<Address> addressList1=userService.getAddressByUserId(21);
        Assert.assertTrue(addressList1.size()==0);
    }

    @Test
    public void updateUsrName() throws Exception{
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s="test@"+df.format(date);
        userService.updateUsrName(s,4);
        User user=userService.findByUserName("aaaaaaaa");
        Assert.assertTrue(user.getUsername().equals(s));
    }

    @Test
    public void updateEmail() throws Exception{
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s="test@"+df.format(date);
        userService.updateEmail(s,4);
        User user=userService.findByUserName("aaaaaaaa");
        Assert.assertTrue(user.getEmail().equals(s));
    }

    @Test
    public void updatePswd() throws Exception{
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s="test@"+df.format(date);
        userService.updatePswd(s,4);
        User user=userService.findByUserName("aaaaaaaa");
        Assert.assertTrue(user.getUserpswd().equals(s));
    }

    @Test
    public void saveUser() throws Exception{
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s="test@"+df.format(date);
        User user=new User(s,s,s,s,date.toString());
        userService.saveUser(user);
        int i=userDao.checkUser(s);
        Assert.assertTrue(i==1);
    }
}