package com.slyk.course.business.controller;

import com.slyk.course.server.domain.Chapter;
import com.slyk.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapter")
    public List<Chapter> chapter() {
        return chapterService.getChapterList();
    }

}
