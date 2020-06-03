package com.crowd.foreground.controller;


import com.crowd.foreground.entity.User;
import com.crowd.foreground.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String toLoginPage(){
        return "login";
    }

    @PostMapping("/user/login")
    public String doLogin(@RequestParam("loginacct") String loginacct,
                          @RequestParam("userpswd") String userpswd,
                          Model model){
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginacct,userpswd);

        //3.执行登录方法
        try {
            subject.login(token);

            //登录成功
            //跳转到test.html
            return "redirect:/projects";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在，UnknownAccountException是Shiro抛出的找不到用户异常
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误，IncorrectCredentialsException是Shiro抛出的密码错误异常
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(){
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }

    @GetMapping("/user/signup")
    public String toSignPage(Model model){
        return "reg";
    }
    @RequestMapping("/user/signup")
    public String signup(@RequestParam("loginacct") String loginacct,
                         @RequestParam("username") String username,
                         @RequestParam("userpswd") String userpswd,
                         @RequestParam("email") String email,
                         Model model){
        Date nowtime = new Date();
        User user=new User(loginacct,userpswd,username,email,nowtime.toString());
        String r=userService.saveUser(user);
        if(r.equals("success")){
            model.addAttribute("msg","注册成功！");
            return "signupsuccess";
        }else {
            model.addAttribute("msg","此账号已被注册！");
            return "reg";
        }
    }
}
