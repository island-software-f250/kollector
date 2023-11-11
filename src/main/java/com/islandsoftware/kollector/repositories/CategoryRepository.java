package com.islandsoftware.kollector.repositories;

import com.islandsoftware.kollector.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
