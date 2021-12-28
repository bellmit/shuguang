package com.sofn.agsjsi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agsjsi.model.ProjectManage;
import com.sofn.agsjsi.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;

public interface ProjectManageService extends IService<ProjectManage> {

    /**
     * 条件查询+分页获取计划管理数据
     * @param pageNo
     * @param pageSize
     * @param name
     * @param orgId
     * @param status
     * @param surveyTimeLeft
     * @param surveyTimeRight
     * @return
     */
    PageUtils<ProjectManage> getAllByQuery(Integer pageNo, Integer pageSize, String name, String orgId, String status, String surveyTimeLeft, String surveyTimeRight);

    /**
     * 添加计划
     * @param projectManage
     */
    void insertProjectManage(ProjectManage projectManage);

    /**
     * 更新计划
     * @param projectManage
     */
    void updateProjectManage(ProjectManage projectManage);

    /**
     * 通过id逻辑删除计划信息
     * @param id
     */
    void deleteProjectManage(String id);

    /**
     * 获取所属机构下拉框
     * @return
     */
    List<DropDownVo> listForDropDown();

    /**
     * 通过id获取对应数据
     * @param id
     * @return
     */
    ProjectManage getProjectManageOne(String id);

    /**
     * 修改状态为已下发
     * @param id
     */
    void updateProjectManageTwo(String id);

}
