package com.slyk.course.server.service;

import com.slyk.course.server.dto.ChapterDto;
import com.slyk.course.server.dto.PageDto;

public interface ChapterService {

    void getChapterList(PageDto<ChapterDto> pageDto);

    void saveOneChapter(ChapterDto chapterDto);
}
