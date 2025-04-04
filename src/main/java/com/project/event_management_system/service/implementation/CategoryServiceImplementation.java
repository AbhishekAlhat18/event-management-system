package com.project.event_management_system.service.implementation;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.AddCategoryDTO;
import com.project.event_management_system.exception.category.CategoryAlreadyExistException;
import com.project.event_management_system.mapper.CategoryMapper;
import com.project.event_management_system.model.Category;
import com.project.event_management_system.repository.CategoryRepository;
import com.project.event_management_system.service.CategoryService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplementation implements CategoryService {
    //Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public ApiResponseDTO<Category> createCategory(AddCategoryDTO addCategoryDTO) {
        if (categoryRepository.existsByName(addCategoryDTO.getName())) {
            throw new CategoryAlreadyExistException("Category with name '" +addCategoryDTO.getName()+ "' already exists.");
        }

        //Covert DTO to Entity
        Category category = categoryMapper.toCategoryEntity(addCategoryDTO);
        categoryRepository.save(category);

        return ApiResponseDTO.<Category>builder()
                .message("Category successful added!")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now().toString())
                .data(category)
                .build();

    }

    @Override
    public List<Category> getCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public void updateCategory(Long id, Category category) {

    }
}
