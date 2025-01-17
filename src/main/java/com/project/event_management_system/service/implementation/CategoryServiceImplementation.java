package com.project.event_management_system.service.implementation;

import com.project.event_management_system.model.Category;
import com.project.event_management_system.repository.CategoryRepository;
import com.project.event_management_system.service.CategoryService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;


public class CategoryServiceImplementation implements CategoryService {
    Logger logger=LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        logger.info("Category Created");
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategory() {
        logger.info("Category List Fetched");
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        logger.info("Category By Id Fetched");
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategory(int id) {
        logger.info("Category Deleted");
        this.categoryRepository.deleteById(id);

    }

    @Override
    @Transactional
    public Category updateCategory(int id, Category category) {
        Category existingCategory = this.categoryRepository.findById(id).get();
        if(category.getCategoryName()!=null)
        {
            existingCategory.setCategoryName(category.getCategoryName());

        }
        logger.info("Category Updated");
        return this.categoryRepository.save(existingCategory);
    }
}
