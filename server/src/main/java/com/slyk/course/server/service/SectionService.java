package com.slyk.course.server.service;

import com.slyk.course.server.dto.SectionDto;
import com.slyk.course.server.dto.SectionPageDto;

public interface SectionService {

    void getSectionList(SectionPageDto pageDto);

    void saveOneSection(SectionDto sectionDto);

    void deleteOneSection(String id) throws Exception;
}

