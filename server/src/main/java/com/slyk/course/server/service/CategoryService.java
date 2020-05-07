package com.slyk.course.server.service;

import com.slyk.course.server.dto.CategoryDto;
import com.slyk.course.server.dto.PageDto;

public interface CategoryService {

    void getCategoryList(PageDto<CategoryDto> pageDto);

    void saveOneCategory(CategoryDto categoryDto);

    void deleteOneCategory(String id) throws Exception;
}

