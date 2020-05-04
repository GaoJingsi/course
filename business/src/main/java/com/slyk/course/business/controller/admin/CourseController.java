package com.slyk.course.business.controller.admin;

import com.slyk.course.server.bo.ResponseBo;
import com.slyk.course.server.dto.CourseDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.service.CourseService;
import com.slyk.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ResponseBo<PageDto<CourseDto>> getCourseList(PageDto<CourseDto> pageDto) {
        try {
            courseService.getCourseList(pageDto);
            return ResponseBo
                    .<PageDto<CourseDto>>builder()
                    .error_no(0)
                    .data(pageDto)
                    .msg("获取课程内容成功！")
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseBo
                    .<PageDto<CourseDto>>builder()
                    .error_no(50000)
                    .data(pageDto)
                    .msg("获取课程内容失败，出现异常！")
                    .build();
        }
    }

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBo<CourseDto> saveOneCourse(@RequestBody CourseDto courseDto) {

        // 保存校验
        ValidatorUtil.require(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        try {
            courseService.saveOneCourse(courseDto);
            return ResponseBo
                    .<CourseDto>builder()
                    .error_no(0)
                    .data(courseDto)
                    .msg("保存成功！")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo
                    .<CourseDto>builder()
                    .error_no(0)
                    .data(courseDto)
                    .msg("保存失败！")
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseBo<Boolean> deleteOneCourse(@PathVariable("id") String id){
        try {
            courseService.deleteOneCourse(id);
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
