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

    /**
     * 根据订单号获取订单信息
     * @param id
     * @return
     */
    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }

    /**
     * 根据订单号更新订单信息，更新订单的支付状态和支付账单号
     * @param id
     * @param status
     * @param payno
     */
    @Override
    public void updateOrderById(Long id, Integer status, String payno) {
        orderDao.updateOrderById(id,status,payno);
    }

    /**
     * 根据订单号获取项目名
     * @param id
     * @return
     */
    @Override
    public String getProjectNameByOrderID(Long id) {
        return orderDao.getProjectNameByOrderID(id);
    }

    /**
     * 根据用户id获取其所有的订单信息
     * @param userid
     * @return
     */
    @Override
    public List<Order> getOrderByUserId(Integer userid) {
        return orderDao.getOrderByUserId(userid);
    }

    /**
     * 根订单号删除订单11
     * @param id
     */
    @Override
    public void deleteOrderById(Long id) {
        orderDao.deleteOrderById(id);
    }
}
