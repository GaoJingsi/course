package com.slyk.course.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slyk.course.server.domain.Section;
import com.slyk.course.server.domain.SectionExample;
import com.slyk.course.server.dto.SectionDto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.dto.SectionPageDto;
import com.slyk.course.server.mapper.SectionMapper;
import com.slyk.course.server.mapper.mine.MyCourseMapper;
import com.slyk.course.server.service.SectionService;
import com.slyk.course.server.utils.CopyUtil;
import com.slyk.course.server.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class SectionServiceImpl implements SectionService {

    private Logger log = LoggerFactory.getLogger(SectionServiceImpl.class);

    @Resource
    private SectionMapper sectionMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Override
    public void getSectionList(SectionPageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        SectionExample.Criteria criteria = sectionExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getChapterId())) {
            criteria.andChapterIdEqualTo(pageDto.getChapterId());
        }
        List<Section> sections = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sections);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtos = CopyUtil.copyList(sections, SectionDto.class);
        pageDto.setRows(sectionDtos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOneSection(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if (StringUtils.isEmpty(section.getId())) {
            String shortUuid = UuidUtil.getShortUuid();
            sectionDto.setId(shortUuid);
            section.setId(shortUuid);
            Date now = new Date();
            section.setCreatedAt(now);
            sectionDto.setCreatedAt(now);
            section.setUpdatedAt(now);
            sectionDto.setUpdatedAt(now);
            sectionMapper.insert(section);
        } else {
            section.setUpdatedAt(new Date());
            sectionMapper.updateByPrimaryKey(section);
        }
        myCourseMapper.updateCourseTime(sectionDto.getCourseId());
    }

    @Override
    public void deleteOneSection(String id) throws Exception {
        int i = sectionMapper.deleteByPrimaryKey(id);
        if (i > 1) {
            throw new Exception("删除了2条或以上的记录！");
        } else if (i < 1) {
            throw new Exception("删除了0条的记录！");
        }
    }
}