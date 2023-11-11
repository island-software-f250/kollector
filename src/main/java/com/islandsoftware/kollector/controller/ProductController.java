package com.islandsoftware.kollector.controller;


import com.islandsoftware.kollector.model.Product;
import com.islandsoftware.kollector.request.ProductRequest;
import com.islandsoftware.kollector.response.ProductResponse;
import com.islandsoftware.kollector.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> products() {
        var products = service.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> product(@PathVariable UUID id) {
        var product = service.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest request) {
        var response = service.registerProduct(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
