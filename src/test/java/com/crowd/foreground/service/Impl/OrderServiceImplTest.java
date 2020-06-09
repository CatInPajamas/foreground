package com.crowd.foreground.service.Impl;

import com.crowd.foreground.ForegroundApplication;
import com.crowd.foreground.entity.Order;
import com.crowd.foreground.service.OrderService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ForegroundApplication.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveOrder() throws Exception{
        Long t=System.currentTimeMillis();
        Date date=new Date();
        Order order=new Order(t,1,1,1,date,100.0,0,1,"test","test");
        orderService.saveOrder(order);
        Order order1=orderService.getOrderById(t);
        Assert.assertEquals("保存账单成功",order.getId(),order1.getId());
    }

    @Test
    public void getOrderById() {
        long id=1591682787952L;
        Order order=orderService.getOrderById(id);
        Assert.assertEquals("获取订单成功",order.getId(),id);
    }

    @Test
    public void updateOrderById() {
        Long id=1591682787952L;
        Date date=new Date();
        String s="test@"+date.toString();
        Integer status=(int)( id % 100 )+1;
        orderService.updateOrderById(id,status,s);
        Order order=orderService.getOrderById(id);
        Assert.assertEquals(status,order.getStatus());
        Assert.assertEquals(s,order.getPayno());
    }

    @Test
    public void getProjectNameByOrderID() {
        Long id=1591682787952L;
        String name=orderService.getProjectNameByOrderID(id);
        Assert.assertEquals("小熊挂烫机",name);
    }

    @Test
    public void getOrderByUserId() {
        List<Order> orderList=orderService.getOrderByUserId(1);
        Assert.assertTrue(!orderList.isEmpty());
        System.out.println(orderList.toString());
    }

    @Test
    public void deleteOrderById() throws Exception {
        Long t=System.currentTimeMillis();
        Order order=new Order(t,1,1,1,new Date(),100.0,0,1,"test","test");
        orderService.saveOrder(order);
        orderService.deleteOrderById(t);
        Order order1=orderService.getOrderById(t);
        Assert.assertTrue(order1==null);
    }
}