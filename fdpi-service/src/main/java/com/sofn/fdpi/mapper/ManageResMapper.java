package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.ManageRes;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 15:49
 */
public interface ManageResMapper extends BaseMapper<ManageRes> {
    List<ManageRes> getManageResList(Map<String, Object> map);
    int deleteManageResInfo(Map<String,Object> map);
}
