package com.slyk.course.business.controller.admin;

import com.slyk.course.server.bo.ResponseBo;
import com.slyk.course.server.dto.ChapterDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/list")
    public ResponseBo<PageDto<ChapterDto>> getChapterList(PageDto<ChapterDto> pageDto) {
        try {
            chapterService.getChapterList(pageDto);
            return ResponseBo
                    .<PageDto<ChapterDto>>builder()
                    .error_no(0)
                    .data(pageDto)
                    .msg("获取大章内容成功！")
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseBo
                    .<PageDto<ChapterDto>>builder()
                    .error_no(50000)
                    .data(pageDto)
                    .msg("获取大章内容失败，出现异常！")
                    .build();
        }
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBo<ChapterDto> saveOneChapter(@RequestBody ChapterDto chapterDto) {
        try {
            chapterService.saveOneChapter(chapterDto);
            return ResponseBo
                    .<ChapterDto>builder()
                    .error_no(0)
                    .data(chapterDto)
                    .msg("保存成功！")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo
                    .<ChapterDto>builder()
                    .error_no(0)
                    .data(chapterDto)
                    .msg("保存失败！")
                    .build();
        }
    }

}
