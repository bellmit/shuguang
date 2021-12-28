package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fdpi.model.ResProcess;
import com.sofn.fdpi.vo.ResProcessFrom;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:11
 */
public interface ResProcessService extends IService<ResProcess> {
    ResProcess insertResProcess(ResProcessFrom resProcessFrom);
}
