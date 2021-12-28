package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.Test;

import java.util.List;

public interface TestMapper extends BaseMapper<Test> {
    List<Test> getTests();
}
