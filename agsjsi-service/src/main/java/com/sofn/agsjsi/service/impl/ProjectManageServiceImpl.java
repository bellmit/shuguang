package com.sofn.agsjsi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.agsjsi.constents.Constants;
import com.sofn.agsjsi.mapper.ProjectManageMapper;
import com.sofn.agsjsi.model.ProjectManage;
import com.sofn.agsjsi.service.ProjectManageService;
import com.sofn.agsjsi.sysapi.SysRegionApi;
import com.sofn.agsjsi.util.SysOwnOrgUtil;
import com.sofn.agsjsi.vo.DropDownVo;
import com.sofn.agsjsi.vo.OrganizationInfo;
import com.sofn.agsjsi.vo.SysOrg;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.sql.Wrapper;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ProjectManageServiceImpl extends BaseService<ProjectManageMapper, ProjectManage> implements ProjectManageService {

    @Autowired
    private ProjectManageMapper projectManageMapper;
    @Autowired
    private SysRegionApi sysRegionApi;

    /**
     * 条件查询加分页获取计划管理
     * @param name
     * @param orgId
     * @param status
     * @param surveyTimeLeft
     * @param surveyTimeRight
     * @return
     */
    @Override
    public PageUtils<ProjectManage> getAllByQuery(Integer pageNo,Integer pageSize,String name, String orgId, String status, String surveyTimeLeft, String surveyTimeRight) {
        Map<String,Object> params = Maps.newHashMap();
        params.put("name", name);
        params.put("orgId",orgId);
        params.put("status",status);
        params.put("surveyTimeLeft", StringUtils.isBlank(surveyTimeLeft) ? "1970-01-01 00:00:00" : surveyTimeLeft);
        params.put("surveyTimeRight", StringUtils.isBlank(surveyTimeRight) ? "2080-01-01 00:00:00" : surveyTimeRight);

        OrganizationInfo orgInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);
        if(!"ministry".equals(orgInfo.getOrganizationLevel())){
            //不是部级，则需要通过当前系统的组织机构id，过滤数据
            params.put("sysOrgId",orgInfo.getRegionLastCode());
        }
        PageHelper.offsetPage(pageNo,pageSize);
        List<ProjectManage> projectManages = projectManageMapper.getAllByQuery(params);
        PageInfo<ProjectManage> pageInfo=new PageInfo<>(projectManages);
        return PageUtils.getPageUtils(pageInfo);
        // 定义结果集
//        List<ProjectManage> result = null;
//        if(orgInfo.getOrganizationLevel().equals("ministry")){
//            // 需要筛选的条件：从列表中筛选出删除标识为"N"的数据
//            List<String> delFlagList = new ArrayList<>();
//            delFlagList.add("N");
//
//            // JDK1.8提供了lambda表达式， 可以从列表中过滤出符合条件的结果。
//            result = projectManages.stream()
//                    .filter((ProjectManage s) -> delFlagList.contains(s.getDelFlag()))
//                    .collect(Collectors.toList());
//        }else {
//            List<ProjectManage> projectManageList = projectManageMapper.getAllByOrgName(orgInfo.getOrganizationName());
//            // 需要筛选的条件：从列表中筛选出删除标识为"N"并且已经下发的计划数据
//            List<String> delFlagList = new ArrayList<>();
//            delFlagList.add("N");
//            List<String> statusList = new ArrayList<>();
//            statusList.add("2");
//            statusList.add("3");
//            // JDK1.8提供了lambda表达式， 可以从列表中过滤出符合条件的结果。
//            result = projectManageList.stream()
//                    .filter((ProjectManage s) -> delFlagList.contains(s.getDelFlag()))
//                    .filter((ProjectManage s) -> statusList.contains(s.getStatus()))
//                    .collect(Collectors.toList());
//            if(result==null){
//                return null;
//            }
//            for (ProjectManage projectManage : result) {
//                if (projectManage.getStatus().equals("3")){
//                    break;
//                }else {
//                    projectManageMapper.updateThree(projectManage.getId());
//                }
//            }
//        }
//        //人工分页
//        List<ProjectManage> newlist = new ArrayList<>();
//        if(pageNo*pageSize>result.size()){
//            newlist=result.subList((pageNo-1)*pageSize, result.size());
//        }else{
//            newlist=result.subList((pageNo-1)*pageSize, pageNo*pageSize);
//        }
//        PageInfo<ProjectManage> pageInfo = new PageInfo<>(newlist);
//        pageInfo.setTotal(result.size());
//        pageInfo.setPageSize(pageSize);
//        pageInfo.setPrePage(pageNo*10);
//        pageInfo.setPageNum(pageNo);
//        return pageInfo;
    }

    /**
     * 添加计划
     * @param projectManage
     */
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void insertProjectManage(ProjectManage projectManage) {
        // 根据名称去重
        ProjectManage one = this.getOne(Wrappers.<ProjectManage>lambdaQuery().eq(ProjectManage::getName, projectManage.getName()));
        if(!ObjectUtils.isEmpty(one) && null!=one){
            throw new SofnException("该计划已被添加");
        }
        User userInfo = SysOwnOrgUtil.getUserInfo();

        projectManage.setId(IdUtil.getUUId());
        projectManage.setCreateTime(new Date());
        projectManage.setCreateUserId(userInfo.getId());
        projectManage.setCreateUserName(userInfo.getNickname());
        //默认状态为已保存，默认删除标识为N
        projectManage.setDelFlag("N");
        projectManage.setStatus("1");
        projectManage.setUpdateTime(new Date());
        projectManage.setUpdateUserId(userInfo.getId());
        projectManage.setUpdateUserName(userInfo.getNickname());
        projectManageMapper.insert(projectManage);
    }

    /**
     * 更新计划
     * @param projectManage
     */
    @Override
    public void updateProjectManage(ProjectManage projectManage) {
        User userInfo = SysOwnOrgUtil.getUserInfo();

        projectManage.setUpdateTime(new Date());
        projectManage.setUpdateUserId(userInfo.getId());
        projectManage.setUpdateUserName(userInfo.getNickname());
        try {
            projectManageMapper.updateById(projectManage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void deleteProjectManage(String id) {
        projectManageMapper.logicDelete(id);
    }

    /**
     * 获取所属机构下拉框
     * @return
     */
    @Override
    public List<DropDownVo> listForDropDown() {
        List<DropDownVo> list= Lists.newArrayList();;
        //获取当前机构的下级机构列表
        OrganizationInfo orgInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);
        if(orgInfo==null){
            return null;
        }
        Result<List<SysOrg>> orgResult = sysRegionApi.getOrgByRegionAndLevel(Constants.SYSTEM_ID, orgInfo.getRegionLastCode(), orgInfo.getOrganizationLevel());
        if(Result.CODE.equals(orgResult.getCode())){
            List<SysOrg> data = orgResult.getData();
            if(!CollectionUtils.isEmpty(data)){
                for(SysOrg sysOrg:data){
                    DropDownVo dropDownVo=new DropDownVo();
                    dropDownVo.setId(sysOrg.getId());
                    dropDownVo.setName(sysOrg.getOrganizationName());
                    list.add(dropDownVo);
                }
            }
        }
        return list;
    }

    @Override
    public ProjectManage getProjectManageOne(String id) {
        ProjectManage oneById = projectManageMapper.getOneById(id);
        projectManageMapper.updateThree(id);
        return oneById;
    }

    @Override
    public void updateProjectManageTwo(String id) {
            projectManageMapper.updateTwo(id);
    }

}
