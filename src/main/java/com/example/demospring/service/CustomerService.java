package com.example.demospring.service;

import com.example.demospring.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customer);
    void deleteCustomer(Long customerId);
}
