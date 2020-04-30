package com.slyk.course.server.service.impl;

import com.slyk.course.server.domain.Chapter;
import com.slyk.course.server.mapper.ChapterMapper;
import com.slyk.course.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    private Logger log = LoggerFactory.getLogger(ChapterServiceImpl.class);

    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> getChapterList() {
        return chapterMapper.selectByExample(null);
    }
}
