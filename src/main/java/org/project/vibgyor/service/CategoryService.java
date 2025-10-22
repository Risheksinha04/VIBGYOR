package org.project.vibgyor.service;

import org.project.vibgyor.entity.Category;
import org.project.vibgyor.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllActiveCategories() {
        return categoryRepository.findByStatusTrue();
    }

    public Category createCategory(Category category) {
        category.setCreatedAt(LocalDateTime.now());
        category.setStatus(true);
        return CategoryRepository.save(category);
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category updateCategory(Integer id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setCategoryName(updatedCategory.getCategoryName());
            category.setDescription(updatedCategory.getDescription());
            category.setUpdatedAt(LocalDateTime.now());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deactivateCategory(Integer id) {
        categoryRepository.findById(id).ifPresent(category -> {
            category.setStatus(false);
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);
        });
    }
}
