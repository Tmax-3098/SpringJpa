package com.saturn.springJpa.controller;

import com.saturn.springJpa.entities.Product;
import com.saturn.springJpa.repositories.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable Long pid){
        Product p = productRepo.findById(pid).orElseThrow(() -> new RuntimeException("product does not exists"));
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
    }
}
