package com.slyk.course.server.service;

import com.slyk.course.server.dto.${Domain}Dto;
import com.slyk.course.server.dto.PageDto;

public interface ${Domain}Service {

    void get${Domain}List(PageDto<${Domain}Dto> pageDto);

    void saveOne${Domain}(${Domain}Dto ${domain}Dto);

    void deleteOne${Domain}(String id) throws Exception;
}

