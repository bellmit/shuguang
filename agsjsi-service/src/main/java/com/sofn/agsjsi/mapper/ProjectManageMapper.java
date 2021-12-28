package com.sofn.agsjsi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjsi.model.ProjectManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectManageMapper extends BaseMapper<ProjectManage> {

    /**
     * 条件查询+分页  农作物信息自动采集
     * @param params
     */
    List<ProjectManage> getAllByQuery(Map params);

    /**
     * 添加数据
     * @param projectManage
     */
    void insertOne(ProjectManage projectManage);


    /**
     * 通过ID获取数据
     * @param id
     * @return
     */
    ProjectManage getOneById(String id);

    /**
     * 更新数据
     * @param projectManage
     */
    void updateOne(ProjectManage projectManage);

    /**
     * 根据id逻辑删除
     * @param id
     */
    void logicDelete(String id);

    /**
     * 修改状态为已下发
     * @param id
     */
    void updateTwo(String id);
    /**
     * 修改状态为已查看
     * @param id
     */
    void updateThree(String id);
    /**
     * 通过当前操作人的所属机构名获取对应数据
     * @param organizationName
     * @return
     */
    List<ProjectManage> getAllByOrgName(String organizationName);
}
