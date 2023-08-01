package com.example.demospring.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospring.model.Product;
import com.example.demospring.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow();
    }

    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = getProductById(productId);
        existingProduct.setName(product.getName());
         existingProduct.setPrice(product.getPrice());
         existingProduct.setStockQuantity(product.getStockQuantity());
        return productRepository.save(existingProduct);
    }

    
    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }
    
    
    public List<Product> getAllProductsFiltered(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        
        return productRepository.findProductsFiltered(name, minPrice, maxPrice);
    }
}
