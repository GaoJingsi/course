package com.slyk.course.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slyk.course.server.domain.Category;
import com.slyk.course.server.domain.CategoryExample;
import com.slyk.course.server.dto.CategoryDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.mapper.CategoryMapper;
import com.slyk.course.server.service.CategoryService;
import com.slyk.course.server.utils.CopyUtil;
import com.slyk.course.server.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void getCategoryList(PageDto<CategoryDto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categorys = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categorys);
        pageDto.setTotal(pageInfo.getTotal());
        List<CategoryDto> categoryDtos = CopyUtil.copyList(categorys, CategoryDto.class);
        pageDto.setRows(categoryDtos);
    }

    @Override
    public void saveOneCategory(CategoryDto categoryDto) {
        Category category = CopyUtil.copy(categoryDto, Category.class);
        if (StringUtils.isEmpty(category.getId())) {
            String shortUuid = UuidUtil.getShortUuid();
            categoryDto.setId(shortUuid);
            category.setId(shortUuid);
            categoryMapper.insert(category);
        } else {
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    @Override
    public void deleteOneCategory(String id) throws Exception {
        int i = categoryMapper.deleteByPrimaryKey(id);
        if (i > 1) {
            throw new Exception("删除了2条或以上的记录！");
        } else if (i < 1) {
            throw new Exception("删除了0条的记录！");
        }
    }
}