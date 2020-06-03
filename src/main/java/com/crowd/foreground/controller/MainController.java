package com.crowd.foreground.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.crowd.foreground.config.AlipayConfig;
import com.crowd.foreground.entity.*;
import com.crowd.foreground.service.OrderService;
import com.crowd.foreground.service.ProjectService;
import com.crowd.foreground.service.UserService;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private Subject subject;

    @GetMapping("/index")
    public String toMainPage(Model model){
        List<ProjectType> typeList=projectService.getAllType();
        Map<Integer,List<ProjectInfo>> map=new HashMap<>();
        for(int i=0;i<typeList.size();i++){
            List<ProjectInfo> projects=projectService.getProjectInfoByTypeid(typeList.get(i).getId());
            map.put(typeList.get(i).getId(),projects);
        }
        model.addAttribute("projects",map);
        model.addAttribute("typeList",typeList);
        return "index";
    }

    @GetMapping("/noAuth")
    public String toNoAuthPage(){
        return "noAuth";
    }

    @RequestMapping("/project")
    public String toProjectDetail(@RequestParam("projectid") Integer projectId,Model model){
        Map<String, String> map=new HashMap<>();
        //获取当前项目的信息
        map=projectService.getProjectDetail(projectId);
        //获取当前项目下的支持项列表
        List<PriceItem> priceItems=projectService.getPriceItemById(projectId);
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        model.addAttribute("user",user);
        model.addAllAttributes(map);
        model.addAttribute("priceItems",priceItems);
        return "project";
    }

    @RequestMapping("/projects")
    public String toProjectsPage(@RequestParam(value ="typeid",defaultValue ="") Integer typeid,
                                 @RequestParam(value ="status",defaultValue ="") Integer status,
                                 @RequestParam(value ="orderby",defaultValue ="0")Integer criteria,
                                 Model model){
        List<ProjectType> typeList=projectService.getAllType();
        List<ProjectInfo> projects=projectService.getProjectInfoByTypeAndStatus(typeid,status,criteria);
        model.addAttribute("projects",projects);
        model.addAttribute("typeList",typeList);
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        model.addAttribute("user",user);
        return "projects";
    }



    @RequestMapping("/user/myfunding")
    public String toMyfunding(Model model){
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        model.addAttribute("user",user);
        List<Order> orderList=orderService.getOrderByUserId(user.getId());
        model.addAttribute("orderList",orderList);
        Map<Integer,String> map=new HashMap<>();
        for(int i=0;i<orderList.size();i++){
            String projectName=projectService.getProjectNameById(orderList.get(i).getProjectid());
            map.put(orderList.get(i).getProjectid(),projectName);
        }
        model.addAttribute("namemap",map);
        return "myfunding";
    }

    @RequestMapping("/user/myfunding/delete_order")
    public String deleteOrder(@RequestParam("orderId")Long orderId){
        orderService.deleteOrderById(orderId);
        return "redirect:/user/myfunding";
    }

    @RequestMapping("/user/userinfo")
    public String toUserInfo(Model model){
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        model.addAttribute("user",user);
        return "userinfo";
    }

    @RequestMapping("/user/address")
    public String toAddAddress(Model model) throws Exception {
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        //获取当前登录用户的地址
        List<Address> addressList=userService.getAddressByUserId(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("addressList",addressList);
        return "useraddress";
    }

    @PostMapping("/user/address/add")
    public String addAddress(@RequestParam("realname")String name,
                           @RequestParam("tel")String tel,
                           @RequestParam("address")String address,
                           Model model) throws Exception {
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Address address1=new Address(user.getId(),tel,address,name);
        userService.addAddress(address1);
        model.addAttribute("user",user);
        return "redirect:/user/address";
    }

    @RequestMapping("/user/address/delete")
    public String deleteAddress(@RequestParam("addressId") Integer addressId) throws Exception {
        userService.deleteAddressById(addressId);
        return "redirect:/user/address";
    }

    @PostMapping("/user/userinfo/update_usrname")
    public String updateUserName(@RequestParam("newUserName") String name) throws Exception {
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        userService.updateUsrName(name,user.getId());
        return "redirect:/user/userinfo";
    }

    @PostMapping("/user/userinfo/update_email")
    public String updateEmail(@RequestParam("newEmail") String email) throws Exception {
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        userService.updateEmail(email,user.getId());
        return "redirect:/user/userinfo";

    }

    @PostMapping("/user/userinfo/update_pswd")
    public String updatePswd(@RequestParam("oldPswd") String oldpswd,
                             @RequestParam("newPswd1") String newpswd1,
                             @RequestParam("newPswd2") String newpswd2,
                             Model model) throws Exception {
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if(newpswd1==newpswd2){
            if(oldpswd==user.getUserpswd()){
                userService.updatePswd(newpswd1,user.getId());
                return "redirect:/user/userinfo";
            }else{
                model.addAttribute("msg","原密码错误");
            }
        }else {
            model.addAttribute("msg","两次输入的密码不一致");
        }
        return "redirect:/user/userinfo";
    }





}
