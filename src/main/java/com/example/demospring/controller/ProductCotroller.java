package com.example.demospring.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospring.model.Product;
import com.example.demospring.service.ProductMetricsService;
import com.example.demospring.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductCotroller {

	    private ProductService productService;

	    private ProductMetricsService productMetricsService;

	    @Autowired
	    public void ProductController(ProductService productService, ProductMetricsService productMetricsService) {
	        this.productService = productService;
	        this.productMetricsService = productMetricsService;
	    }

//	    @GetMapping
//	    public ResponseEntity<List<Product>> getAllProducts() {
//	        List<Product> products = productService.getAllProducts();
//	        return new ResponseEntity<>(products, HttpStatus.OK);
//	    }
	    
	    @GetMapping
	    public ResponseEntity<List<Product>> getAllProducts(
	            @RequestParam(value = "name", required = false) String name,
	            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
	            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice
	            // Add other filter parameters if needed
	    ) {
	        List<Product> products = productService.getAllProductsFiltered(name, minPrice, maxPrice);
	        return new ResponseEntity<>(products, HttpStatus.OK);
	    }

//	    @GetMapping("/{productId}")
//	    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
//	        Product product = productService.getProductById(productId);
//	        return new ResponseEntity<>(product, HttpStatus.OK);
//	    }
	    
	    @GetMapping("/{productId}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
	        Product product = productService.getProductById(productId);
	        productMetricsService.incrementProductRequestCounter(productId.toString());
	        return new ResponseEntity<>(product, HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product createdProduct = productService.createProduct(product);
	        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	    }

	    @PutMapping("/{productId}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
	        Product updatedProduct = productService.updateProduct(productId, product);
	        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	    }

	    @DeleteMapping("/{productId}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
	        productService.deleteProduct(productId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    @PostMapping("/request")
	    public ResponseEntity<List<Product>> getProductsWithFilter(@RequestParam(value = "productName", required = false) String productName,
	                                                               @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
	                                                               @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice) {
	        List<Product> filteredProducts = productService.getAllProductsFiltered(productName, minPrice, maxPrice);
	        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
	    }
	}

