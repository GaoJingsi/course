package com.slyk.server.service.impl;

import com.slyk.server.mapper.TestMapper;
import com.slyk.server.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    private Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

    @Resource
    private TestMapper testMapper;

    public List<Map<String, Object>> test() {
        log.info("测试热部署");
        return testMapper.test();
    }
}
