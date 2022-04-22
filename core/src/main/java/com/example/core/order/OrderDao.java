package com.example.core.order;

import java.util.List;

public interface OrderDao {

    default boolean insertOrder(Order order){
        return insertOrder(order);
    }

    List<Order> selectAll();
}
