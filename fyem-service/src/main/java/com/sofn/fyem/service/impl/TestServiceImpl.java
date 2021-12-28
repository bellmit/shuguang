package com.sofn.fyem.service.impl;

import com.sofn.common.service.BaseService;
import com.sofn.fyem.mapper.TestMapper;
import com.sofn.fyem.model.Test;
import com.sofn.fyem.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "testService")
@Slf4j
public class TestServiceImpl extends BaseService<TestMapper, Test> implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public List<Test> getTests() {
        return testMapper.getTests();
    }
}
