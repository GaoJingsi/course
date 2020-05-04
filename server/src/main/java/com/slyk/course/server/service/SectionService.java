package com.slyk.course.server.service;

import com.slyk.course.server.dto.SectionDto;
import com.slyk.course.server.dto.PageDto;

public interface SectionService {

    void getSectionList(PageDto<SectionDto> pageDto);

    void saveOneSection(SectionDto sectionDto);

    void deleteOneSection(String id) throws Exception;
}

