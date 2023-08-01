package com.example.demospring.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demospring.model.Product;

@Service
public interface ProductService 
{

    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    List<Product> getAllProductsFiltered(String name, BigDecimal minPrice, BigDecimal maxPrice);

}
