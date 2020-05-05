package com.slyk.course.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slyk.course.server.domain.Course;
import com.slyk.course.server.domain.CourseExample;
import com.slyk.course.server.dto.CourseDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.mapper.CourseMapper;
import com.slyk.course.server.service.CourseService;
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
import java.util.Date;

@Service
public class CourseServiceImpl implements CourseService {

    private Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Resource
    private CourseMapper courseMapper;

    @Override
    public void getCourseList(PageDto<CourseDto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courses = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtos = CopyUtil.copyList(courses, CourseDto.class);
        pageDto.setRows(courseDtos);
    }

    @Override
    public void saveOneCourse(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(course.getId())) {
            String shortUuid = UuidUtil.getShortUuid();
            courseDto.setId(shortUuid);
            course.setId(shortUuid);
            Date now = new Date();
            course.setCreatedAt(now);
            courseDto.setCreatedAt(now);
            course.setUpdatedAt(now);
            courseDto.setUpdatedAt(now);
            courseMapper.insert(course);
        } else {
            course.setUpdatedAt(new Date());
            courseMapper.updateByPrimaryKey(course);
        }
    }

    @Override
    public void deleteOneCourse(String id) throws Exception {
        int i = courseMapper.deleteByPrimaryKey(id);
        if (i > 1) {
            throw new Exception("删除了2条或以上的记录！");
        } else if (i < 1) {
            throw new Exception("删除了0条的记录！");
        }
    }

    @Override
    public CourseDto getCourseById(String id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        CourseDto courseDto = CopyUtil.copy(course, CourseDto.class);
        return courseDto;
    }
}