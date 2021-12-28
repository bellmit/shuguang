package com.sofn.agpjpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjpm.model.FileManage;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:28
 */
public interface FileManageMapper extends BaseMapper<FileManage> {
    List<FileManage> listByParams(Map map);
}
