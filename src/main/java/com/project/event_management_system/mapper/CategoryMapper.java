package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.AddCategoryDTO;
import com.project.event_management_system.model.Category;

public interface CategoryMapper {
    Category toCategoryEntity(AddCategoryDTO addCategoryDTO);
}
