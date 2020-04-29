package com.slyk.course.service;

import com.slyk.course.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    public List<Map<String, Object>> test() {
        return testMapper.test();
    }
}
