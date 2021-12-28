package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysRole;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SystemRoleVo;
import com.sofn.sys.vo.UserHaveRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by sofn
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

        /**
         * 新增或修改角色时，判断角色名是否重复
         * @param roleName 角色名
         * @param id 角色id(新增时为null)
         * @return 大于0，表示重复
         */
        Integer getSysRoleByName(@Param("roleName") String roleName,@Param("subsystemId") String subsystemId, @Param("id") String id);

        List<SysRole> getSysRoleByCondition(Map<String,Object> params);
        List<SysRole> selectRolesByUserId(String userId);
        List<SysRole>  selectRolesByIds(String[] ids);
        int insertSelective(SysRole record);
        SysRole selectByPrimaryKey(String id);
        int updateByPrimaryKeySelective(SysRole record);

        /**
         * 获取用户有的角色和用户自己创建的角色
         * @param params   userId 用户ID   必传
         *                 status 状态
         *                 subsystemId   子系统ID
         *                 ids    需要排除的ID
         * @return  List<SysRoleVo>
         */
        List<SysRoleVo> selectUserHasRole(Map<String,Object> params);

        /**
         * 获取当前用户有的角色，不包含用户自己创建的
         * @return  List<SysRoleVo>
         */
        List<SysRoleVo> selectUserHasRoleAndNotIncludeUserCreate(Map<String,Object> params);

        /**
         * 根据系统代码获取角色列表
         * @param appId   系统代码
         * @return  List<SysRole>
         */
        List<SysRole> getRoleListByAppId(@Param("appId") String appId);

        /**
         * 获取每个角色有哪些权限
         * @return   Map<String,String>
         *     ROLEID   角色ID
         *     ROLECODE   角色代码
         *     PERMISSION   角色所对应的权限
         */
        List<Map<String,String>> getRoleHasPermission();


        /**
         * 批量获取用户有的角色
         * @param userIds   用户ID
         * @return   Map<String,List<SysRole>>   key为用户ID   List为用户有的角色
         */
        List<UserHaveRoleVo> getUserHaveRole(@Param("userIds") List<String> userIds);

        /**
         * 按条件查询系统和角色信息
         * @param keyword  系统名称或者角色名称或者角色代码或者系统代码
         * @param userId   用户编号，用于限制用户可查询的角色
         * @param roleIds  角色IDs
         * @return  List<SystemRoleVo>
         */
        List<SystemRoleVo> getSystemAndRoleInfo(@Param("keyword") String keyword,
                                                @Param("userId") String userId,
                                                @Param("roleIds") List<String> roleIds);

}
