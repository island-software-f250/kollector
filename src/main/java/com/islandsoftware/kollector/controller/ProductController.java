package com.islandsoftware.kollector.controller;


import com.islandsoftware.kollector.model.Category;
import com.islandsoftware.kollector.model.Product;
import com.islandsoftware.kollector.repositories.ProductRepository;
import com.islandsoftware.kollector.request.ProductRequest;
import com.islandsoftware.kollector.response.ProductResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/")
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

    @GetMapping("/{id}")
    public ResponseEntity<Product> product(@PathVariable UUID id) {
        var productOptional = repository.findById(id);

        return productOptional.map(
                product -> new ResponseEntity<>(product, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product")
    public ResponseEntity<Product> register(@RequestBody ProductRequest request){
        Product product = new Product();

        BeanUtils.copyProperties(request, product);

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product save = repository.save(product);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

}
