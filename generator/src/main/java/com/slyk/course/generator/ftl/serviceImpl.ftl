package com.slyk.course.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slyk.course.server.domain.${Domain};
import com.slyk.course.server.domain.${Domain}Example;
import com.slyk.course.server.dto.${Domain}Dto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.mapper.${Domain}Mapper;
import com.slyk.course.server.service.${Domain}Service;
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
<#list typeSet as type>
    <#if type=='Date'>
import java.util.Date;
    </#if>
</#list>

@Service
public class ${Domain}ServiceImpl implements ${Domain}Service {

    private Logger log = LoggerFactory.getLogger(${Domain}ServiceImpl.class);

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    @Override
    public void get${Domain}List(PageDto<${Domain}Dto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        <#list fieldList as field>
            <#if field.nameHump=='sort'>
        ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>
        List<${Domain}> ${domain}s = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}s);
        pageDto.setTotal(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}Dtos = CopyUtil.copyList(${domain}s, ${Domain}Dto.class);
        pageDto.setRows(${domain}Dtos);
    }

    @Override
    public void saveOne${Domain}(${Domain}Dto ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        if (StringUtils.isEmpty(${domain}.getId())) {
            String shortUuid = UuidUtil.getShortUuid();
            ${domain}Dto.setId(shortUuid);
            ${domain}.setId(shortUuid);
            <#list typeSet as type>
                <#if type=='Date'>
            Date now = new Date();
                </#if>
            </#list>
            <#list fieldList as field>
                <#if field.nameHump=='createdAt'>
            ${domain}.setCreatedAt(now);
            ${domain}Dto.setCreatedAt(now);
                </#if>
                <#if field.nameHump=='updatedAt'>
            ${domain}.setUpdatedAt(now);
            ${domain}Dto.setUpdatedAt(now);
                </#if>
            </#list>
            ${domain}Mapper.insert(${domain});
        } else {
            <#list fieldList as field>
                <#if field.nameHump=='updatedAt'>
            ${domain}.setUpdatedAt(new Date());
                </#if>
            </#list>
            ${domain}Mapper.updateByPrimaryKey(${domain});
        }
    }

    @Override
    public void deleteOne${Domain}(String id) throws Exception {
        int i = ${domain}Mapper.deleteByPrimaryKey(id);
        if (i > 1) {
            throw new Exception("删除了2条或以上的记录！");
        } else if (i < 1) {
            throw new Exception("删除了0条的记录！");
        }
    }
}