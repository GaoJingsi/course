package com.slyk.course.server.service.impl;

import com.slyk.course.server.domain.Test;
import com.slyk.course.server.mapper.TestMapper;
import com.slyk.course.server.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

    @Resource
    private TestMapper testMapper;

    public List<Test> test() {
        log.info("测试热部署");
        return testMapper.selectByExample(null);
    }
}
