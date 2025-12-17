package com.saturn.springJpa.controller;

import com.saturn.springJpa.entities.Product;
import com.saturn.springJpa.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepo productRepo;

    @Value("${page.size}")
    private int PAGE_SIZE;

    public ProductController(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable Long pid){
        Product p = productRepo.findById(pid).orElseThrow(() -> new RuntimeException("product does not exists"));
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{quantity}")
    public ResponseEntity<List<Product>> getProductByQuantity(@PathVariable Integer quantity){
        //List<Product> products = productRepo.findByQuantityGreaterThan(quantity, Sort.by(Sort.Direction.DESC, "quantity"));
        List<Product> products = productRepo.findByQuantityGreaterThan(quantity, Sort.by(Sort.Order.asc("quantity"), Sort.Order.desc("price")));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Page<Product>> getProductByPrice(@RequestParam BigDecimal price, @RequestParam Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum, PAGE_SIZE, Sort.by("brand").ascending());
        return new ResponseEntity<>(productRepo.findByPriceGreaterThan(price, pageable), HttpStatus.OK);
    }
}
