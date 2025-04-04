package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.AddCategoryDTO;
import com.project.event_management_system.model.Category;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategoryEntity(AddCategoryDTO addCategoryDTO) {
        Category category = new Category();
        category.setName(addCategoryDTO.getName());
        return category;
    }
}
