package com.slyk.course.business.controller.admin;

import com.slyk.course.server.bo.ResponseBo;
import com.slyk.course.server.dto.CategoryDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.service.CategoryService;
import com.slyk.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    public static final String BUSINESS_NAME = "分类";

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseBo<PageDto<CategoryDto>> getCategoryList(PageDto<CategoryDto> pageDto) {
        try {
            categoryService.getCategoryList(pageDto);
            return ResponseBo
                    .<PageDto<CategoryDto>>builder()
                    .error_no(0)
                    .data(pageDto)
                    .msg("获取分类内容成功！")
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseBo
                    .<PageDto<CategoryDto>>builder()
                    .error_no(50000)
                    .data(pageDto)
                    .msg("获取分类内容失败，出现异常！")
                    .build();
        }
    }

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBo<CategoryDto> saveOneCategory(@RequestBody CategoryDto categoryDto) {

        // 保存校验
        ValidatorUtil.require(categoryDto.getParent(), "父id");
        ValidatorUtil.require(categoryDto.getName(), "名称");
        ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);

        try {
            categoryService.saveOneCategory(categoryDto);
            return ResponseBo
                    .<CategoryDto>builder()
                    .error_no(0)
                    .data(categoryDto)
                    .msg("保存成功！")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo
                    .<CategoryDto>builder()
                    .error_no(0)
                    .data(categoryDto)
                    .msg("保存失败！")
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseBo<Boolean> deleteOneCategory(@PathVariable("id") String id){
        try {
            categoryService.deleteOneCategory(id);
            return ResponseBo
                    .<Boolean>builder()
                    .error_no(0)
                    .data(true)
                    .msg("删除成功！")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo
                    .<Boolean>builder()
                    .error_no(0)
                    .data(true)
                    .msg(e.getMessage())
                    .build();
        }
    }

}
