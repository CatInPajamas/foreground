package com.crowd.foreground.dao;

import com.crowd.foreground.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderDao {

    @Insert("INSERT INTO `t_order`(`id`,`userid`, `projectid`, `itemid`, `createtime`, `money`, `status`, `addressid`, `remark`,`payno`) VALUES (#{id},#{userid},#{projectid},#{itemid},#{createtime},#{money},#{status},#{addressid},#{remark},#{payno})")
    void saveOrder(Order order);

    @Select("select *from t_order where id=#{id}")
    Order getOrderById(Long id);

    @Select("select *from t_order where userid=#{userid}")
    List<Order> getOrderByUserId(Integer userid);

    @Update("UPDATE t_order SET payno=#{payno},status=#{status} WHERE id=#{id}")
    void updateOrderById(Long id,Integer status,String payno);

    @Select("select t_project.name\n" +
            "from t_project LEFT JOIN t_order on t_project.id=t_order.projectid\n" +
            "WHERE t_order.id=#{id}")
    String getProjectNameByOrderID(Long id);

    @Delete("delete from t_order where id=#{id}")
    void deleteOrderById(Long id);
}
