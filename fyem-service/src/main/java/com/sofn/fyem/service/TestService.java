package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fyem.model.Test;

import java.util.List;

public interface TestService extends IService<Test> {
    /**
     * 测试数据库表
     * @return
     */
    List<Test> getTests();
}
