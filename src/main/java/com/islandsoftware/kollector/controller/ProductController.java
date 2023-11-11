package com.islandsoftware.kollector.controller;


import com.islandsoftware.kollector.model.Category;
import com.islandsoftware.kollector.model.Product;
import com.islandsoftware.kollector.repositories.ProductRepository;
import com.islandsoftware.kollector.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> products() {
        var products = repository.findAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProductResponse> productResponseList = products.stream().map((Product p) ->
                        new ProductResponse(p.getName(), p.getDescription(), p.getSpecification(),
                                p.getCategories()
                                        .stream().map(Category::getName)
                                        .collect(Collectors.toSet()), p.getQuantity(), p.getPrice(), p.isActive()))
                .toList();

        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }


}
