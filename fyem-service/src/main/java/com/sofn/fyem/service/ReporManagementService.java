package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.ReporManagement;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface ReporManagementService extends IService<ReporManagement> {

    /**
     * 获取上报信息List(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<ReporManagement>
     */
    PageUtils<ReporManagement> getReporManagementListByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 获取上报信息List(不分页)
     * @param params 查询条件
     * @return List<ReporManagement>
     */
    List<ReporManagement> getReporManagementListByQuery(Map<String,Object> params);


    /**
     * 获取上报信息详情
     * @param id id
     * @return ReporManagement
     */
    ReporManagement getReporManagementById(String id);

    /**
     * 修改上报信息状态
     * @param params params
     */
    String updateStatus(Map<String,Object> params);

    /**
     * 新增上报信息
     * @param reporManagement reporManagement
     */
    void addReporManagement(ReporManagement reporManagement);

    /**
     * 修改上报信息
     * @param reporManagement reporManagement
     */
    void updateReporManagement(ReporManagement reporManagement);;


    /**
     * 删除上报信息
     * @param id id
     */
    void removeReporManagement(String id);

    void deleteReporManagement(Map<String,Object> params);

    /**
     * 判断当前用户是否可以修改 belongYear 数据
     * @param belongYear
     * @return
     */
    boolean able2Modify(String belongYear);

    /**
     * 判断当前用户是否可以修改 belongYear 数据状态
     * @param belongYear
     * @return
     */
    boolean able2Audit(String belongYear);

    /**
     * 标记当前用户所在组织机构的belongYear成提交状态
     * @param belongYear
     */
    void markSubmit(String belongYear);

    /**
     * 标记当前用户所在组织机构的belongYear成驳回状态
     * @param params {"belongYear":"", "provinceId":"", "cityId":"", "countyId":""}
     */
    void markReject(Map<String, Object> params);
}
