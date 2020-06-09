package com.crowd.foreground.service.Impl;

import com.crowd.foreground.ForegroundApplication;
import com.crowd.foreground.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForegroundApplication.class)
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveOrder() {

    }

    @Test
    public void getOrderById() {
    }

    @Test
    public void updateOrderById() {
    }

    @Test
    public void getProjectNameByOrderID() {
    }

    @Test
    public void getOrderByUserId() {
    }

    @Test
    public void deleteOrderById() {
    }
}