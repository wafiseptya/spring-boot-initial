package com.example.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("list")
public class OrderRepository implements OrderDao{
    private static List<Order> DB = new ArrayList<>();
    @Override
    public boolean insertOrder(Order order) {
        DB.add(new Order(order.getName(), order.getEmail(), order.getAddress(), order.getPhone_number(), order.getPhone_brand(), order.getDamage()));
        return true;
    }

    @Override
    public List<Order> selectAll() {
        return DB;
    }
}
