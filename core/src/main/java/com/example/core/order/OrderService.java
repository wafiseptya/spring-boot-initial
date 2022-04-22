package com.example.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderService(@Qualifier("list") OrderDao orderRepository) {
        this.orderDao = orderRepository;
    }

    public boolean addOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    public List<Order> getAllOrder() {
        return orderDao.selectAll();
    }
}
