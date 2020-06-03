package com.crowd.foreground.service;

import com.crowd.foreground.entity.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order) throws Exception;

    Order getOrderById(Long id);

    void updateOrderById(Long id,Integer status,String payno);

    String getProjectNameByOrderID(Long id);

    List<Order> getOrderByUserId(Integer userid);

    void deleteOrderById(Long id);
}
