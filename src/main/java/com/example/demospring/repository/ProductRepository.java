package com.example.demospring.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demospring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{


   

    @Query("SELECT p FROM Product p WHERE "
           + "(:name IS NULL OR p.name LIKE %:name%) "
           + "AND (:minPrice IS NULL OR p.price >= :minPrice) "
           + "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findProductsFiltered(@Param("name") String name,
                                       @Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice);

   

}
