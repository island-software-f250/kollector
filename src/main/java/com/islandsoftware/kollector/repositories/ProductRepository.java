package com.islandsoftware.kollector.repositories;

import com.islandsoftware.kollector.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
