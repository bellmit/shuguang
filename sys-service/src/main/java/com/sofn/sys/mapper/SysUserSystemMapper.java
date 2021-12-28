package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysUserSystem;

import java.util.List;
import java.util.Map;

/**
 * @author luoyinghui
 */
public interface SysUserSystemMapper extends BaseMapper<SysUserSystem> {

    List<SysUserSystem> getListByConditions(Map<String,Object> params);
/*    //删除某个用户最久远的一条数据
    void deleteLastData(String userId);*/
}
