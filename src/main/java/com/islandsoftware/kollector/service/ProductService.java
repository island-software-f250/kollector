package com.islandsoftware.kollector.service;

import com.islandsoftware.kollector.exceptions.DataNotFoundException;
import com.islandsoftware.kollector.model.Category;
import com.islandsoftware.kollector.model.Product;
import com.islandsoftware.kollector.repositories.ProductRepository;
import com.islandsoftware.kollector.request.ProductRequest;
import com.islandsoftware.kollector.response.ProductResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product getProductById(UUID uuid) throws DataNotFoundException {
        var productOptional = productRepository.findById(uuid);

        return productOptional.orElseThrow(() -> new DataNotFoundException("Sorry! product not found."));
    }

    public List<ProductResponse> getAllProducts() throws DataNotFoundException {
        var products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new DataNotFoundException("Sorry! No products found.");
        }

        return products.stream().map((Product p) ->
                new ProductResponse(p.getProductId(), p.getName(), p.getDescription(), p.getSpecification(),
                        categoryService.extractCategoryNames(p.getCategories()),
                        p.getQuantity(), p.getPrice(), p.isActive())).toList();
    }

    public ProductResponse registerProduct(ProductRequest request) {
        var product = new Product();

        BeanUtils.copyProperties(request, product);

        Set<Category> categories = categoryService.getCategoriesById(request.categories());

        product.setCategories(categories);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

        return new ProductResponse(product.getProductId(), product.getName(), product.getDescription(),
                product.getSpecification(), categoryService.extractCategoryNames(product.getCategories()),
                product.getQuantity(), product.getPrice(), product.isActive());
    }
}
