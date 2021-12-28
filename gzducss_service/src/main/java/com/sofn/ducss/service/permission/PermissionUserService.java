package com.sofn.ducss.service.permission;

import com.sofn.ducss.vo.SysUserForm;
import com.sofn.ducss.util.PageUtils;

import java.util.Map;

/**
 * 用户权限相关
 * @author heyongjie
 * @date 2019/12/25 10:19
 */
public interface PermissionUserService {

    /**
     *
     * @param pageNo   从哪条记录开始
     * @param pageSize   每页显示多少条
     * @param params     参数
     * @return   用户分页信息
     */
    PageUtils<SysUserForm> getLoginUserCanShowUser(Integer pageNo, Integer pageSize, Map<String, Object> params);


}
