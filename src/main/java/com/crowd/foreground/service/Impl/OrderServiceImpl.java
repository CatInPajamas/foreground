package com.crowd.foreground.service.Impl;

import com.crowd.foreground.dao.OrderDao;
import com.crowd.foreground.entity.Order;
import com.crowd.foreground.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public void saveOrder(Order order) throws Exception {
        orderDao.saveOrder(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public void updateOrderById(Long id, Integer status, String payno) {
        orderDao.updateOrderById(id,status,payno);
    }

    @Override
    public String getProjectNameByOrderID(Long id) {
        return orderDao.getProjectNameByOrderID(id);
    }

    @Override
    public List<Order> getOrderByUserId(Integer userid) {
        return orderDao.getOrderByUserId(userid);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDao.deleteOrderById(id);
    }
}
