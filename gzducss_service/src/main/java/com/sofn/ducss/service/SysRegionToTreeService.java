package com.sofn.ducss.service;

import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 将行政区划形成树结构服务层
 *
 * @author heyongjie
 */
public interface SysRegionToTreeService {

    /**
     * 将行政区划形成树结构
     *
     * @return 行政区划树
     */
    SysRegionTreeVo getSysRegionTree(String appId, Integer versionYear);

    /**
     * 按条件反向递归树
     *
     * @param regionName 行政区划名称
     * @param parentId   行政区划ID或者ParentId
     * @param regionCode 行政区划ID
     * @return SysRegionTreeVo
     */
    SysRegionTreeVo getSysRegionTree(String appId, String regionName, String parentId, String regionCode, Integer versionYear);

    /**
     * 获取所有父节点
     *
     * @param regionCode 行政区划代码
     * @return List<SysRegionTreeVo>
     */
    List<SysRegionTreeVo> getParentNode(String regionCode, Integer versionYear);

    /**
     * 根据id获取下级子菜单
     *
     * @param id parentId
     * @return List<SysRegionTreeVo>  所有的下级机构
     */
    List<SysRegionTreeVo> getSysRegionTreeById(String appId, String id, Integer versionYear);

    /**
     * 将行政区划形成树结构
     *
     * @param token 用户登录TOKEN
     * @param level 机构级别
     * @param orgId 如果出现多个，需要传入一个机构ID，明确使用哪个机构的机构级别
     * @return 行政区划树
     */
    SysRegionTreeVo getSysRegionTreeByLoginUser(String token, String level, String orgId, String appId, Integer versionYear);

    /**
     * 从缓存中获取行政区划树结构
     *
     * @return 行政区划树
     */
    SysRegionTreeVo getSysRegionTreeByCache(String appId, Integer versionYear);

    /**
     * 通过缓存获取根据id获取下级子菜单数据
     *
     * @param id parentId
     * @return List<SysRegionTreeVo>  所有的下级机构
     */
    List<SysRegionTreeVo> getSysRegionTreeByIdAndCache(String appId, String id, Integer versionYear);

    /**
     * 根据ParentId查询子区划， parentId必传， 分页参数有任何一个没传入就不分页
     *
     * @param params   参数
     * @param pageNo   从哪条记录开始
     * @param pageSize 每页显示显示条数
     * @return
     */
    PageUtils<SysRegionTreeVo> getSysRegionFormByParentId(Map<String, String> params, Integer versionYear, Integer pageNo, Integer pageSize);

    /**
     * 获取所有父节点
     * @param regionId  行政区划代码
     * @return  List<SysRegionTreeVo>
     */
    List<SysRegionTreeVo> getParentNodeByRegionId(String regionId, Integer versionYear);


}
