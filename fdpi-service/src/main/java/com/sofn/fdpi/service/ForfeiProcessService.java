package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fdpi.model.ForfeiProcess;
import com.sofn.fdpi.vo.ForfeiProcessFrom;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 10:56
 */
public interface ForfeiProcessService extends IService<ForfeiProcess> {
     ForfeiProcess insertForfeiProcess(ForfeiProcessFrom from);
}
