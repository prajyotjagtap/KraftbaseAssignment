package com.example.demospring.service;

import com.example.demospring.model.Customer;
import com.example.demospring.model.Order;
import com.example.demospring.repository.CustomerRepository;
import com.example.demospring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    
    private final CustomerRepository customerRepository;
    
//    @Autowired
//    public OrderServiceImpl(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
    
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

//    @Override
//    public Order createOrder(Order order) {
//        return orderRepository.save(order);
//    }
    
    @Override
    public Order createOrder(Order order) {
        
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Order must have a customer.");
        }

         Long customerId = order.getCustomer().getId();
        Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
        if (existingCustomer == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " not found.");
        }

         return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(orderId).orElse(null);
        if (existingOrder != null) {
            existingOrder.setCustomer(updatedOrder.getCustomer());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
            existingOrder.setStatus(updatedOrder.getStatus());

            return orderRepository.save(existingOrder);
        }
        return null; 
    }

//    @Override
//    public void deleteOrder(Long orderId) {
//        Order existingOrder = getOrderById(orderId);
//        if (existingOrder != null) {
//            orderRepository.delete(existingOrder);
//        }
//    }
    
    @Override
    public boolean deleteOrder(Long orderId) {
        Order existingOrder = orderRepository.findById(orderId).orElse(null);
        if (existingOrder != null) {
            orderRepository.delete(existingOrder);
            return true; 
        }
        return false; 
    }
}
