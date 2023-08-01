package com.example.demospring.service;

import com.example.demospring.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    Order createOrder(Order order);
    Order updateOrder(Long orderId, Order order);
    boolean deleteOrder(Long orderId);
}
