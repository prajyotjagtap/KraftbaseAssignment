package com.example.demospring.controller;

import com.example.demospring.model.Order;
import com.example.demospring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Order order) 
    {
        Order updatedOrder = orderService.updateOrder(orderId, order);
        if (updatedOrder != null) 
        {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } 
        else 
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
 // Method to handle DELETE request for deleting an order by orderId
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        // Call the deleteOrder method from the OrderService
        // This method will handle the deletion and return true if successful, false otherwise.
        boolean isDeleted = orderService.deleteOrder(orderId);

        if (isDeleted) {
            // Return a success response if the order was deleted successfully
            return ResponseEntity.ok("Order with ID " + orderId + " has been deleted.");
        } else {
            // Return an error response if the order was not found or unable to be deleted
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order with ID " + orderId + " not found or could not be deleted.");
        }
    }
}
