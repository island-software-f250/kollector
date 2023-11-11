package com.islandsoftware.kollector.service;

import com.islandsoftware.kollector.model.Category;
import com.islandsoftware.kollector.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    public Set<Category> getCategoriesById(List<UUID> uuidList){
        return uuidList.stream()
            .map(categoryUUID -> repository.findById(categoryUUID))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
    }

    public Set<String> extractCategoryNames(Set<Category> categories) {
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }
}
