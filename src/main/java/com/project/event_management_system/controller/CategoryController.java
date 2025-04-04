package com.project.event_management_system.controller;


import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.AddCategoryDTO;
import com.project.event_management_system.model.Category;
import com.project.event_management_system.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class CategoryController {

private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add-category")
    public ResponseEntity<ApiResponseDTO<Category>> createCategory(@RequestBody @Valid AddCategoryDTO createCategoryRequest) {
        ApiResponseDTO<Category> response = this.categoryService.createCategory(createCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
