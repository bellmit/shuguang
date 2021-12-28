package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.vo.SysSubsystemTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 */
public interface SysSubsystemMapper extends BaseMapper<SysSubsystem> {

    /**
     * 根据子系统名称或者系统代码获取数量
     * @param name   子系统名称
     * @param appId  系统代码
     * @param id  系统ID    不等于
     * @return   系统数量
     */
    Integer getSysSubsystemByNameOrAppId(@Param("name") String name, @Param("appId") String appId, @Param("id") String id);

    /**
     * 批量删除
     * @param ids  待删除ID
     * @param updateUserId  更新用户ID
     * @param updateTime   更新时间
     */
    void batchDelete(@Param("ids") List<String> ids,@Param("updateUserId") String updateUserId,
                     @Param("updateTime") Date updateTime);

    /**
     * 根据系统AppId获取菜单信息
     * @param appId  系统AppId
     * @return  SysSubsystem
     */
    SysSubsystem getSysSubsystemAppId(String appId);

    /**
     * 根据角色ID获取系统信息
     * @param ids  角色IDS
     * @return   List<SysSubsystem>
     */
    List<SysSubsystem> getSysSubsystemByRoleId(@Param("ids") List<String> ids);

    /**
     * 按条件获取系统信息
     * @param params  subsystemName  系统名称模糊查询
     *                appId    系统代码 模糊查询
     *                parentId   父系统ID  模糊查询
     *                delFlag    是否删除
     *
     * @return  系统信息
     */
    List<SysSubsystem> getSysSubsystemByCondition(Map<String, Object> params);

    /**
     * 根据系统ID获取系统信息
     * @param ids  系统ID
     * @return   系统信息
     */
    List<SysSubsystem> selectSubsystemByIds(String[] ids);


    /**
     * 根据系统ID获取系统信息
     * @param subSystemId  系统ID
     * @return  系统信息
     */
    List<SysSubsystem> getParentIdsById(@Param(value = "subSystemId") String subSystemId);

    /**
     * 获取当前用户有的子系统
     * @param params   roleIds  用户有的角色ID
     *                 rootLevel   子系统根节点代码
     * @return          List<SysSubsystemTreeVo>
     */
    List<SysSubsystemTreeVo> selectUserHasSubsystem(Map<String,Object> params);

    /**
     * 获取树结构基本信息
     * @param params  参数
     * @return    List<SysSubsystemTreeVo>
     */
    List<SysSubsystemTreeVo> selectSysSystemTreeVo(Map<String,Object> params);

    /**
     * 获取所有的系统代码集合
     * @return  所有的系统代码
     */
    List<String> selectAllAppIdList();

    /**
     * 当前用户有的AppId
     * @param userId   用户ID
     * @return  List<String>
     */
    List<String> getUserHaveAppId(String userId);

    /**
     * 根据AppId模糊匹配系统信息
     * @param appIds   系统代码
     * @return   List<SysSubsystemTreeVo>
     */
    List<SysSubsystemTreeVo> getSystemInfoLikeAppIds(@Param("appIds") List<String> appIds);


    /**
     * 更新parentIds字段
     * @param parentIds   parentIds
     * @param ids   需要更新的ID
     */
    void updateParentId(@Param("parentIds") String parentIds, @Param("ids")List<String> ids);


    /**
     * 根据IDS更新子节点
     * @param ids   待更新的子节点ID
     * @param newParentIdPrefix  新的parentIds前缀
     * @param oldParentIdPrefix  旧的parentIds前缀
     */
    void updateParentIdsByIds(@Param("ids") List<String> ids,@Param("newParentIdPrefix") String newParentIdPrefix,
                              @Param("oldParentIdPrefix") String oldParentIdPrefix);

}
