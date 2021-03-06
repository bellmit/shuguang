package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.SysRoleGroup;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.SysRoleGroupForm;
import com.sofn.ducss.vo.SysSubsystemTreeVo;

import java.util.List;
import java.util.Map;

/**
 * Created by sofn
 */
public interface SysRoleGroupService extends IService<SysRoleGroup> {
    PageUtils<SysRoleGroupForm> findAllGroupList(Map<String, Object> params, Integer pageNo, Integer pageSize);
    void createRoleGroup(SysRoleGroupForm sysGroup);
    String updateRoleGroup(SysRoleGroupForm sysGroup);
    void deleteRoleGroup(String[] ids);
    List<SysRoleGroupForm> getSysRoleGroupByContion(Map<String, Object> params);

    /**
     *  获取当前用户有的子系统和角色 返回格式为List<SysSubsystemTreeVo>  SysSubsystemTreeVo.child = roles
     * @return List<SysSubsystemTreeVo>
     */
    List<SysSubsystemTreeVo> getSubsystemHasRoleToTree() ;

}
