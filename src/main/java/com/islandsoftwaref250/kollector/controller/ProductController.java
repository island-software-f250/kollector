package com.islandsoftwaref250.kollector.controller;


import com.islandsoftwaref250.kollector.exceptions.ProductSaveException;
import com.islandsoftwaref250.kollector.request.ProductRequest;
import com.islandsoftwaref250.kollector.response.ProductResponse;
import com.islandsoftwaref250.kollector.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> products() {
        var products = service.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> product(@PathVariable UUID id) {
        var product = service.getOneProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest request) {
        try {
            var response = service.registerProduct(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ProductSaveException("Error registering the product");
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> edit(@PathVariable UUID id, @RequestBody @Valid ProductRequest request) {
        try {
            var response = service.updateProduct(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ProductSaveException("Error updating the product");
        }
    }
}
