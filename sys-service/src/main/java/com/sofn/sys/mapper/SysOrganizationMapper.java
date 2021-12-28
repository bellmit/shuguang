package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysOrganization;
import com.sofn.sys.vo.SysOrganizationTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by sofn
 */
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {

    int updateSalefParentIds(String makeSelfAsParentIds);

    Integer getSysOrganizationByName(@Param("orgname") String orgname, @Param("id") String id);

    Integer getUserOfOrganization(@Param("id") String id);

    List<SysOrganization> selectOrganizationListByPriority();

    List<SysOrganization> getSysOrganizationByContion(Map<String,Object> params);

    List<SysOrganization> getRootSysOrg();

    List<SysOrganization> getOrgTree(@Param("sysid") String sysId);

    /**
     * 根据名字和IDS 查询机构
     * @param orgName   机构名称
     * @param ids   ID
     * @return     机构信息
     */
    List<Map<String,String>> getInfoByCondition(@Param("orgName") String  orgName,
                                                @Param("ids") List<String> ids);


    /**
     * 根据条件获取树节点信息
     * @param params  查询条件
     *                ids    根据ID 集合查询信息 List
     *                parentIdList   父节点ID集合  List
     *                parentIds     所有级别关系 如root/node1/node2/node3....
     * @return    List<SysOrganizationTreeVo>
     */
    List<SysOrganizationTreeVo> getTreeVoInfoByCondition(Map<String,Object> params);

    /**
     *
     *  根据条件获取树节点信息
     *  这个不会进行翻译  相对较快
     * @param params  参数
     * @return    List<SysOrganizationTreeVo>
     */
    List<SysOrganizationTreeVo> getBasicTreeVoInfoByCondition(Map<String,Object> params);

    /**
     * 根据IDS 根据parentIds字段的值
     * @param parentIds   修改后的值
     * @param ids   哪些值需要修改
     */
    void updateParentIdsByIds(@Param("parentIds") String parentIds,@Param("ids") List<String> ids);

    /**
     * 根据行政区划找到代理机构
     * @param regionId   行政区划
     * @param appId      子系统AppId
     * @param orgId      行政机构ID
     * @return  List<SysOrganizationTreeVo>
     */
    List<SysOrganizationTreeVo> getProxyOrgByRegionIdAndAppId(@Param("regionId") String regionId,@Param("appId") String appId,@Param("orgId") String orgId);


}
