package com.crowd.foreground.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

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
            return "redirect:/success";
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

    @GetMapping("/success")
    public String s(){
        return "success";
    }
}
