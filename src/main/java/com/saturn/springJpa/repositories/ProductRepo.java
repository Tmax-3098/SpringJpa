package com.saturn.springJpa.repositories;

import com.saturn.springJpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceGreaterThan(BigDecimal price);
    List<Product> findDistinctByBrand(String brand);
    List<Product> findByPriceGreaterThanAndQuantityLessThan(BigDecimal price, Integer quantity);

    @Query("select e from Product e where e.brand=?1")
    Optional<Product> findByBrand(String brand);
}
