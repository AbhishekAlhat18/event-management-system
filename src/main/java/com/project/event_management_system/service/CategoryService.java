package com.project.event_management_system.service;

import com.project.event_management_system.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public Category createCategory(Category category);

    public List<Category> getCategory();

    public Category getCategoryById(int id);

    public void deleteCategory(int id);

    public Category updateCategory(int id, Category category);



}
