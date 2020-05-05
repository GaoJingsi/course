package com.slyk.course.server.service;

import com.slyk.course.server.dto.CourseDto;
import com.slyk.course.server.dto.PageDto;

public interface CourseService {

    void getCourseList(PageDto<CourseDto> pageDto);

    void saveOneCourse(CourseDto courseDto);

    void deleteOneCourse(String id) throws Exception;

    CourseDto getCourseById(String id);

    /**
     * 更新课程总时长
     */
    void udpateCourseTime(String courseId);
}

