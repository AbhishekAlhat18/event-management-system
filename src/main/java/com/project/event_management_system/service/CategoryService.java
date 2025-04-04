package com.project.event_management_system.service;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.AddCategoryDTO;
import com.project.event_management_system.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public ApiResponseDTO<Category> createCategory(AddCategoryDTO addCategoryDTO);

    public List<Category> getCategories();

    public Category getCategoryById(Long id);

    public void deleteCategory(Long id);

    public void updateCategory(Long id, Category category);



}
