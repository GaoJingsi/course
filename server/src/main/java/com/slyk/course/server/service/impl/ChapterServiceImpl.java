package com.slyk.course.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slyk.course.server.domain.Chapter;
import com.slyk.course.server.dto.ChapterDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.mapper.ChapterMapper;
import com.slyk.course.server.service.ChapterService;
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

@Service
public class ChapterServiceImpl implements ChapterService {

    private Logger log = LoggerFactory.getLogger(ChapterServiceImpl.class);

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public void getChapterList(PageDto<ChapterDto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<Chapter> chapters = chapterMapper.selectByExample(null);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapters);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtos = CopyUtil.copyList(chapters, ChapterDto.class);
        pageDto.setRows(chapterDtos);
    }

    @Override
    public void saveOneChapter(ChapterDto chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if (StringUtils.isEmpty(chapter.getId())) {
            String shortUuid = UuidUtil.getShortUuid();
            chapterDto.setId(shortUuid);
            chapter.setId(shortUuid);
            chapterMapper.insertSelective(chapter);
        } else {
            chapterMapper.updateByPrimaryKey(chapter);
        }
    }

    @Override
    public void deleteOneChapter(String id) throws Exception {
        int i = chapterMapper.deleteByPrimaryKey(id);
        if (i > 1) {
            throw new Exception("删除了2条或以上的记录！");
        } else if (i < 1) {
            throw new Exception("删除了0条的记录！");
        }
    }
}
