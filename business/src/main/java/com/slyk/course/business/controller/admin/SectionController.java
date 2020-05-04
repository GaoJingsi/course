package com.slyk.course.business.controller.admin;

import com.slyk.course.server.bo.ResponseBo;
import com.slyk.course.server.dto.SectionDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.service.SectionService;
import com.slyk.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";

    @Autowired
    private SectionService sectionService;

    @GetMapping("")
    public ResponseBo<PageDto<SectionDto>> getSectionList(PageDto<SectionDto> pageDto) {
        try {
            sectionService.getSectionList(pageDto);
            return ResponseBo
                    .<PageDto<SectionDto>>builder()
                    .error_no(0)
                    .data(pageDto)
                    .msg("获取小节内容成功！")
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseBo
                    .<PageDto<SectionDto>>builder()
                    .error_no(50000)
                    .data(pageDto)
                    .msg("获取小节内容失败，出现异常！")
                    .build();
        }
    }

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBo<SectionDto> saveOneSection(@RequestBody SectionDto sectionDto) {

        // 保存校验
        ValidatorUtil.require(sectionDto.getTitle(), "标题");
        ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);

        try {
            sectionService.saveOneSection(sectionDto);
            return ResponseBo
                    .<SectionDto>builder()
                    .error_no(0)
                    .data(sectionDto)
                    .msg("保存成功！")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo
                    .<SectionDto>builder()
                    .error_no(0)
                    .data(sectionDto)
                    .msg("保存失败！")
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseBo<Boolean> deleteOneSection(@PathVariable("id") String id){
        try {
            sectionService.deleteOneSection(id);
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
