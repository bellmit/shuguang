package com.sofn.sys.service.permission;

import com.sofn.common.treeutil.service.TreeService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.vo.SysOrgVo;
import com.sofn.sys.vo.SysOrganizationTreeVo;

import java.util.List;
import java.util.Map;

/**
 * 机构权限
 *
 * 因为之前组织机构信息所构造的树结构不满足权限相关树的生成，所以继承TreeService使用新的Tree生成
 *
 * @author heyongjie
 * @date 2019/12/17 13:58
 */
public interface PermissionOrganizationService extends TreeService<SysOrganizationTreeVo> {

    /**
     * 获取当前登录用户的机构ID、下级所有机构ID
     * @return  所有的机构
     */
    List<String> getOrgIdsByLoginUser();

    /**
     * 获取从哪个节点开始的树
     * @param orgId   机构ID
     * @param regionId    区划ID
     * @param orgName    机构名称
     * @return   SysOrganizationTreeVo  从根节点开始的树
     */
    SysOrganizationTreeVo getPermissionOrganizationTree(String orgId,String regionId,String orgName);

    /**
     * 获取当前登录用户的机构ID、下级所有机构ID、代理机构ID
     * @return List<String>
     */
    List<String> getOrgIdByLoginUserAndAgent();

    /**
     * 根据ParentId或者其他条件查询当前用户有的子系统  只有parentId是根节点才进行分页，否则不进行分页
     * 获取当前用户有的机构，只返回一层，如果带了除系统标识和父节点ID以外的参数不分页（返回的分页信息无效），并且不止返回一层
     * @param params   参数  ： parentId和  appId必传  其他参数： orgName机构名称   regionCode 区划代码， isThree  是否第三方
     * @param pageNo  从哪条数据开始     parentId不是根节点时可以为空
     * @param pageSize  每页显示多少条   parentId不是根节点时可以为空
     * @return PageUtils<SysOrgVo>
     */
    PageUtils<SysOrgVo> getUserHaveOrgByParentIdAndOtherParam(Map<String,Object> params, Integer pageNo, Integer pageSize);

    /**
     * 获取当前用户有的机构的基本信息，不返回各个值翻译后的信息，会返回当前用户有的机构信息
     * @return  SysOrganizationTreeVo
     */
    SysOrganizationTreeVo getUserHaveOrgBaseInfoToTree();


}
