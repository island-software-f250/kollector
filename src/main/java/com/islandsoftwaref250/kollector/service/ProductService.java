package com.islandsoftwaref250.kollector.service;

import com.islandsoftwaref250.kollector.exceptions.DataNotFoundException;
import com.islandsoftwaref250.kollector.exceptions.ProductSaveException;
import com.islandsoftwaref250.kollector.model.Category;
import com.islandsoftwaref250.kollector.model.Product;
import com.islandsoftwaref250.kollector.repositories.ProductRepository;
import com.islandsoftwaref250.kollector.request.ProductRequest;
import com.islandsoftwaref250.kollector.response.ProductResponse;
import jakarta.transaction.Transactional;
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
    
    private Product mapProductRequestToProduct(ProductRequest request) {
        var product = new Product();

        BeanUtils.copyProperties(request, product);

        Set<Category> categories = categoryService.getCategoriesById(request.categories());

        product.setCategories(categories);

        return product;
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        return new ProductResponse(product.getProductId(), product.getName(), product.getDescription(),
                product.getSpecification(), categoryService.extractCategoryNames(product.getCategories()),
                product.getQuantity(), product.getPrice(), product.isActive());
    }

    private Product getProductById(UUID uuid) throws DataNotFoundException {
        var productOptional = productRepository.findById(uuid);

        return productOptional.orElseThrow(() -> new DataNotFoundException("Sorry! product not found."));
    }

    @Transactional
    public ProductResponse getOneProduct(UUID uuid) throws DataNotFoundException {
        var product = this.getProductById(uuid);
        return this.mapProductToProductResponse(product);
    }

    @Transactional
    public List<ProductResponse> getAllProducts() throws DataNotFoundException {
        var products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new DataNotFoundException("Sorry! No products found.");
        }

        return products.stream()
                .map(this::mapProductToProductResponse)
                .toList();
    }

    @Transactional
    public ProductResponse registerProduct(ProductRequest request) throws ProductSaveException {
        var product = this.mapProductRequestToProduct(request);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

        return mapProductToProductResponse(product);
    }

    @Transactional
    public ProductResponse updateProduct(UUID id, ProductRequest request) throws DataNotFoundException, ProductSaveException {
        var product = this.getProductById(id);
        var productConverted = this.mapProductRequestToProduct(request);

        product.setName(productConverted.getName());
        product.setDescription(productConverted.getDescription());
        product.setSpecification(productConverted.getSpecification());
        product.setCategories(productConverted.getCategories());
        product.setQuantity(productConverted.getQuantity());
        product.setPrice(productConverted.getPrice());
        product.setActive(productConverted.isActive());
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

        return mapProductToProductResponse(product);
    }

}
