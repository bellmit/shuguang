package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.vo.BasicUserInfo;
import com.sofn.sys.vo.Excel.ImportSysUserVo;
import com.sofn.sys.vo.SelectUserConditionVo;
import com.sofn.sys.vo.SysUserForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by sofn
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 按条件查询用户  限制
     * @param params  条件      limitOrg  限制机构  orgId  当前用户的机构
     * @return
     */
    List<SysUser> getSysUserByCondition(Map<String,Object> params);


    List<SysUser> getUserListBySubsystem(Map<String,Object> params);
    /**
     * 批量添加用户
     * @param sysUsers   需要添加的用户信息
     */
    void batchSave(@Param("sysUsers") List<SysUser> sysUsers);
    SysUser selectByPrimaryKey(String id);
    int insertSelective(SysUser record);
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 根据IDS 将用户信息翻译成详细信息
     * @param ids   IDS
     * @return        * @return
     */
    List<SysUserForm> getUserInfoByIds(@Param("ids") List<String> ids);

    /**
     * 根据用户查询角色信息
     * @param userId   用户ID
     * @return   角色信息
     */
    List<String> getRoleIdsByUserId(@Param("userId") String userId);

    /**
     * 批量获取角色权限信息
     * @param roleIds   需要查询的角色信息
     * @return  List<Map<String,String>>
     */
    List<Map<String,String>> getRoleInfoByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 获取用户基本信息   没有角色、机构、权限列表等信息
     * @param selectUserConditionVo  查询VO
     * @return  List<BasicUserInfo>
     */
    List<BasicUserInfo> getBasicUserInfo(SelectUserConditionVo selectUserConditionVo);
    List<BasicUserInfo> getBasicUserInfoNew(SelectUserConditionVo selectUserConditionVo);

    /**
     * 获取导出用户信息
     * @param params  参数
     * @return  List<ImportSysUserVo>
     */
    List<ImportSysUserVo> getExportUserInfo(Map<String,String> params);

    /**
     * 根据角色ID和子系统ID获取用户
     * @param params  roleId  subsystemId  username  nickname
     * @return   List<BasicUserInfo>
     */
    List<BasicUserInfo> getUserByRoleIdAndSubsystemId(Map<String,Object> params);

    /**
     * 根据信息获取不满足
     * @param params  roleId  subsystemId  username  nickname
     * @return   List<BasicUserInfo>
     */
    List<BasicUserInfo> getUserByNotExistsRoleId(Map<String,Object> params);

    /**
     * 获取区域下使用系统的用户
     * @param appId 系统id
     * @param regionCodeList 行政区域集
     * @author du.yang
     * @date 2021-03-04 17:00:00
     */
    List<SysUser> getUserUsingApp(@Param("appId") String appId, @Param("regionCodeList") List<String> regionCodeList);

}
