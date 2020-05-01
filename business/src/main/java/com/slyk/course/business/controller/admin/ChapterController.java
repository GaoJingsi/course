package com.slyk.course.business.controller.admin;

import com.slyk.course.server.domain.Chapter;
import com.slyk.course.server.response.ResponseBo;
import com.slyk.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/admin/chapter")
    public ResponseBo<List<Chapter>> chapter() {
        return ResponseBo
                .<List<Chapter>>builder()
                .error_no(0)
                .data(chapterService.getChapterList())
                .msg("获取大章内容成功！")
                .build();
    }

}
