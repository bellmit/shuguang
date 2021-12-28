package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceApplication;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface TDgapResourceApplicationProvider extends BaseProvider<TDgapResourceApplication> {

    PageInfo<Map<String, Object>> list(Map<String, Object> params);
    /**
     * 分页加条件查询
     * @param paramas
     * @return
     */
    PageInfo<Map<String,Object>> pageList(Map<String, Object> paramas);

    /**
     * 查询资源名称
     * @return
     */
    List<TDgapResource> getResource();
    /**
     * 根据id查询一条记录
     * @return
     */
    Map<String,Object> getResourceById(String id);

    public void batchDelete(String[] ids);

    /**
     * 获取申请人
     * @return
     */
    List<TDgapResourceApplication> getUser();


    List<String> queryCallerToken();

    int countByResourceId(String resourceId);

    int countByResources(String[] ids);

    /**
     * 通过用户id获取资源
     * @param userId
     * @return
     */
    TDgapResourceApplication getbyParam(String userId);

    List<TDgapResourceApplication> getByResourceType(String resourceType);

    /**
     * 通过地方平台对接申请id
     * @param localPlatformAppliId
     * @return
     */
    List<TDgapResourceApplication> getbyParams(String localPlatformAppliId);

    /**
     * 根据资源id和token获得地方平台对接申请
     * @param token
     * @return
     */
    TDgapResourceApplication  getAppliByToken(String resourceId,String token);

    /**
     * 所有资源审批详情列表
     * @param map
     * @return
     */
    PageInfo<Map<String, Object>> listDeatails(Map<String, Object> map);

    /**
     * 根据id获取资源申请详情
     * @param id
     * @return
     */
    Map<String, Object> getResourceApplicationDetails(String id);
}
