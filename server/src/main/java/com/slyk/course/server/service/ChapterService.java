package com.slyk.course.server.service;

import com.slyk.course.server.dto.ChapterDto;
import com.slyk.course.server.dto.ChapterPageDto;
import com.slyk.course.server.dto.PageDto;

public interface ChapterService {

    void getChapterList(ChapterPageDto pageDto);

    void saveOneChapter(ChapterDto chapterDto);

    void deleteOneChapter(String id) throws Exception;
}
