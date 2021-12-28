package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by heyongjie on 2019/6/11 9:59
 */
public interface IntegrationMapper extends BaseMapper<SysUserRole> {

    void updateSysUser(SysUser sysUser);

}
