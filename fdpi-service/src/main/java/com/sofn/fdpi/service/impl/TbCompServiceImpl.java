package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.mapper.ChangeRecordProcessMapper;
import com.sofn.fdpi.mapper.CompSpeStockFlowMapper;
import com.sofn.fdpi.mapper.TbCompMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysOrganizationForm;
//import com.sofn.fdpi.util.RedisCompUtil;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.util.SysFileManageUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service("tbCompService")
public class TbCompServiceImpl extends ServiceImpl<TbCompMapper, TbComp> implements TbCompService {
    @Autowired
    private TbCompMapper tbCompMapper;

    @Resource
    private SignboardApplyService signboardApplyService;

    @Resource
    private SignboardProcessService signboardProcessService;

    @Resource
    private SignboardService signboardService;

    @Resource
    private SignboardApplyListService signboardApplyListService;

    @Resource
    private SignboardChangeRecordService signboardChangeRecordService;

    @Resource
    private SignboardPrintService signboardPrintService;

    @Resource
    private CompSpeStockService compSpeStockService;

    @Resource
    private CompSpeStockFlowMapper compSpeStockFlowMapper;

    @Resource
    private ChangeRecordService changeRecordService;

    @Resource
    private ChangeRecordProcessMapper changeRecordProcessMapper;

//    @Resource
//    private TransferService transferService;

    @Resource
    private SturgeonService sturgeonService;

    @Resource
    private SturgeonSubService sturgeonSubService;

    @Resource
    private SturgeonSignboardService sturgeonSignboardService;

    @Resource
    private SturgeonSignboardDomesticService sturgeonSignboardDomesticService;

    @Resource
    private SturgeonProcessService sturgeonProcessService;

    @Resource
    private PapersService papersService;

    @Resource
    private PapersSpecService papersSpecService;

    @Resource
    private PapersYearInspectService papersYearInspectService;

    @Resource
    private TbUsersService tbUsersService;

    @Resource
    private PapersYearInspectProcessService papersYearInspectProcessService;

    @Autowired
    private TbDepartmentService tbDepartmentService;

    @Autowired
    private SysRegionApi sysRegionApi;


    @Override
    public PageUtils<TbCompVo> listByPage(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<TbCompVo> list = tbCompMapper.ListByCondition(map);
        PageInfo<TbCompVo> pageInfo = new PageInfo<>(list);
        return PageUtils.getPageUtils(pageInfo);
    }


    /**
     * @param map 查询参数
     * @return List<TbCompVo> 对象
     * @Description: 企业查询-列表
     * @Author: wuXY
     * @Date: 2019-12-30 11:31:41
     */
    @Override
    public PageUtils<TbCompVo> listForCompAndYearInspectByPage(Map<String, Object> map, int pageNo, int pageSize) {
        map.put("userOrgId", UserUtil.getLoginUserOrganizationId());
        String compName = map.get("compName") == null ? "" : map.get("compName").toString();
        if (StringUtils.isNotBlank(compName)) {
            //企业名称不为空，先全匹配查询，没有找到数据，在模糊查询
            map.put("compFullName", compName);
            map.put("compName", "");
            //针对精确查找赋予区域条件
            this.getRegion(map);
            PageHelper.offsetPage(pageNo, pageSize);
            List<TbCompVo> tbCompVoList = tbCompMapper.listForCompAndYearInspect(map);
            if (!CollectionUtils.isEmpty(tbCompVoList)) {
                PageInfo<TbCompVo> pageInfo = new PageInfo<>(tbCompVoList);
                return PageUtils.getPageUtils(pageInfo);
            } else {
                map.put("compFullName", "");
                map.put("compName", compName);
            }
        }
        //直属-省级-部级-执法部门
        if (map.get("compFullName") == null || StringUtils.isBlank(map.get("compFullName").toString())) {
            //精确查询为空字符串，则需要进行数据控制
            Result<Map<String, Object>> whereMapResult = SysOwnOrgUtil.getDataWhereMapForDifferenceLevel(false);
            if (Result.CODE.equals(whereMapResult.getCode())) {
                Map<String, Object> whereMap = whereMapResult.getData();
                map.putAll(whereMap);
            } else {
                return null;
            }
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<TbCompVo> tbCompVoList = tbCompMapper.listForCompAndYearInspect(map);
        PageInfo<TbCompVo> pageInfo = new PageInfo<>(tbCompVoList);
        return PageUtils.getPageUtils(pageInfo);
    }

    //针对精确查找赋予区域条件
    private void getRegion(Map<String, Object> map) {
        OrganizationInfo oi = SysOwnOrgUtil.getOrgInfo();
        String level = oi.getOrganizationLevel();
        if ("city".equals(level)) {
            map.put("sysOrgProvince", oi.getRegionLastCode().substring(0, 2) + "0000");
        } else if ("county".equals(level)) {
            map.put("sysOrgCity", oi.getRegionLastCode().substring(0, 4) + "00");
        }
    }

    /**
     * 根据获取企业信息
     * wuXY
     * 2019-12-30 11:31:41
     *
     * @param id 企业id编号
     * @return TbComp对象
     */
    @Override
    public TbCompVo getCombById(String id) {
        return tbCompMapper.getCombById(id);
    }

    /**
     * 根据企业id编号修改企业信息
     * wuXY
     * 2019-12-30 11:31:41
     *
     * @param comp 企业对象
     * @return 操作成功
     */
    @Override
    public String updateComById(TbComp comp) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_comp_updateComById");
        //先进行文件处理
        TbComp tbCompInDB = this.getById(comp);
        Result result1 = SysFileManageUtil.dealFileInUploadForm(comp.getBusLicenseFileId(), tbCompInDB.getBusLicenseFileId());
        if (Result.CODE.equals(result1.getCode())) {
            //处理文件成功
            comp.setBusLicenseFileId(result1.getMsg());
            comp.setUpdateTime(new Date());
            comp.setUpdateUserId(UserUtil.getLoginUserId());
            int result = tbCompMapper.updateComById(comp);
            if (result == 0) {
                return "修改企业信息失败！";
            }
            return "1";
        }
        return result1.getMsg();
    }

    /**
     * 根据企业id修改企业状态
     *
     * @param map 查询条件
     * @return "1"：成功；其他异常
     */
    @Override
    public String updateStatusById(Map<String, Object> map) {
        int result = tbCompMapper.updateStatusById(map);
        if (result == 0) {
            return "修改企业状态失败！";
        }
        return "1";
    }

    /**
     * 获取当前企业的直属企业信息（id和level）
     * wuXY
     * 2020-1-9 17:23:24
     *
     * @param compId 企业id
     * @return DepartmentLevelVo 直属企业信息
     */
    @Override
    public DepartmentLevelVo getDeptLevel(String compId) {
        return tbCompMapper.getDeptLevel(compId);
    }

    /**
     * 直属机构修改企业的行政区划
     * wuXY
     * 2020-1-15 15:33:57
     *
     * @param tbCompRegionForm 修改的对象
     * @return 1：成功 or 其他失败
     */
    @Override
    public String updateCompRegionById(TbCompRegionForm tbCompRegionForm) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_comp_updateCompRegionById");
        //验证当前机构是否是直属机构
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ORG_ID", UserUtil.getLoginUserOrganizationId());
        queryWrapper.eq("DEL_FLAG", "N");
        queryWrapper.eq("TYPE", "1");

        TbDepartment directOrg = tbDepartmentService.getOne(queryWrapper);
        if (directOrg == null) {
            return "当前用户不是直属机构，不能修改企业行政区划！";
        }
        //重新通过行政区划获取直属机构
        DepartmentLevelVo newDirectOrg = tbDepartmentService.getRedirectTempId(tbCompRegionForm.getCompProvince(), tbCompRegionForm.getCompCity(), tbCompRegionForm.getCompDistrict());
        if (newDirectOrg == null) {
            return "该行政区划无对应的直属机构，请修改！";
        }
        //获取新机构的级别
        Result<SysOrganizationForm> sysOrgResult = sysRegionApi.getOrgInfoById(newDirectOrg.getSysDeptId());
        if (!Result.CODE.equals(sysOrgResult.getCode())) {
            return sysOrgResult.getMsg();
        }
        SysOrganizationForm sysOrgModel = sysOrgResult.getData();
        if (sysOrgModel == null) {
            return "获取支撑平台组织机构失败！";
        }

        TbComp tbComp = tbCompRegionForm.getTbComp(tbCompRegionForm);
        tbComp.setDireclyId(newDirectOrg.getSysDeptId());
        tbComp.setDirectOrgLevel(sysOrgModel.getOrganizationLevel());
        tbComp.setUpdateUserId(UserUtil.getLoginUserId());
        tbComp.setUpdateTime(new Date());
        //修改企业信息
        int result = tbCompMapper.updateCompRegionById(tbComp);
        if (result > 0) {
            return "1";
        }
        return "修改失败！";
    }

    /**
     * 获取所有企业名称和账号；企业名称和账号是1:1，可以写成一个，后期是1：n，则需要分开
     * wXY
     *
     * @return List<CompAndUserVo>
     */
    @Override
    public List<CompAndUserVo> listForAllCompAndUser() {
        return tbCompMapper.listForAllCompAndUser();
    }

    @Override
    public void loadCompAndUserDataToCache() {
        System.out.println(">>>>>>>>>>>>>加载缓存数据开始<<<<<<<<<<<<<<<");
        List<CompAndUserVo> list = this.listForAllCompAndUser();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(v -> {
//                RedisCompUtil.addRegisterCompInCacheForHash(v.getCompName());
//                RedisCompUtil.addRegisterUserNameInCacheForHash(v.getUserName());
            });
        }
        System.out.println(">>>>>>>>>>>>>加载缓存数据结束<<<<<<<<<<<<<<<");
    }

    @Override
    public int getCompCount(String status) {
        return tbCompMapper.getCompCount(status);
    }

    @Override
    public String getTodayMaxApplyNum(String todayStr) {
        return tbCompMapper.getTodayMaxApplyNum(todayStr);
    }

    @Override
    public String getMaxCompCode() {
        return tbCompMapper.getMaxCompCode();
    }

    @Override
    public boolean validCompIsHasRegisterAndSave(String compName) {
        QueryWrapper<TbComp> queryWrapper = new QueryWrapper<>();
        List<String> status = Arrays.asList("0", "1");
        queryWrapper.eq("del_flag", BoolUtils.N)
                .eq("comp_name", compName).in("comp_status", status);
        return !CollectionUtils.isEmpty(tbCompMapper.selectList(queryWrapper));
    }

    @Override
    public TbComp getCombByCode(String compCode) {
        QueryWrapper<TbComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("comp_code", compCode).orderByDesc("create_time");
        List<TbComp> tbComps = tbCompMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(tbComps)) {
            return tbComps.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Map<String, Integer> delete(String compId) {
        Map<String, Integer> result = new HashMap<>();
        //所有标识申请id
        List<String> signboardApplyIds = signboardApplyService.listApplyByCompId(compId).
                stream().map(SignboardApply::getId).collect(Collectors.toList());
        //删除标识申请流程
        result.put("signboardApplyProcessCount", signboardProcessService.delByApplyIds(signboardApplyIds));
        //删除标识申请
        result.put("signboardApplyCount", signboardApplyService.delByCompId(compId));
        //删除标识
        result.put("signboardCount", signboardService.delByCompId(compId));
        //删除标识申请列表
        result.put("signboardApplyListCount", signboardApplyListService.delByApplyIds(signboardApplyIds));
        //删除标识变更流程
        result.put("signboardChangeRecordCount", signboardChangeRecordService.delByCompId(compId));
        //删除标识打印
        result.put("signboardPrintCount", signboardPrintService.delByCompId(compId));
        //所有鱼子酱申请id
        List<String> sturgeonIds = sturgeonService.listApplyByCompId(compId).
                stream().map(Sturgeon::getId).collect(Collectors.toList());
        //删除鱼子酱申请流程
        result.put("sturgeonProcessCount", sturgeonProcessService.delByApplyIds(sturgeonIds));
        //删除鱼子酱子表
        result.put("sturgeonSubCount", sturgeonSubService.delBySturgeonIds(sturgeonIds));
        //删除鱼子酱标识(国外)
        result.put("sturgeonSignboardCount", sturgeonSignboardService.delByCompId(compId));
        //删除鱼子酱标识(国内)
        result.put("sturgeonSignboardDomesticCount", sturgeonSignboardDomesticService.delByCompId(compId));
        //删除物种库存
        result.put("compSpeStoctCount", compSpeStockService.delByCompId(compId));
        //删除物种库存流水记录
        QueryWrapper<CompSpeStockFlow> compSpeStockFlowWrapper = new QueryWrapper<>();
        compSpeStockFlowWrapper.eq("comp_id", compId);
        result.put("compSpeStockFlowCount", compSpeStockFlowMapper.delete(compSpeStockFlowWrapper));
        //变更记录id
        List<String> changeRecordIds = changeRecordService.
                listRecordByCompId(compId).stream().map(ChangeRecord::getId).collect(Collectors.toList());
        //删除变更记录流程
        if (!CollectionUtils.isEmpty(changeRecordIds)) {
            QueryWrapper<ChangeRecordProcess> cangeRecordProcessWrapper = new QueryWrapper<>();
            cangeRecordProcessWrapper.in("change_record_id", changeRecordIds);
            result.put("changeRecordProcessCount", changeRecordProcessMapper.delete(cangeRecordProcessWrapper));
        }
        //所有证书id
        List<String> papersIds = papersService.
                listPaperByCompId(compId).stream().map(Papers::getId).collect(Collectors.toList());
        //删除证书物种
        result.put("papersSpecCount", papersSpecService.delByPaperIds(papersIds));
        //删除证书
        result.put("papersCount", papersService.delByCompId(compId));
        //证书年审ids
        List<String> papersYearInspectIds = papersYearInspectService.
                listYearInspectByCompId(compId).stream().map(PapersYearInspect::getId).collect(Collectors.toList());
        //删除年审流程
        result.put("papersYearInspectProcessCount", papersYearInspectProcessService.delByYearInspectIds(papersYearInspectIds));
        //TODO 删除年审记录
        //删除企业
        result.put("compCount", tbCompMapper.deleteById(compId));
        //获取企业用户
        List<TbUsers> tbUsers = tbUsersService.getUserByCompId(compId);
        if (!CollectionUtils.isEmpty(tbUsers)) {
            List<String> userIds = tbUsers.stream().map(TbUsers::getId).collect(Collectors.toList());
            //删除用户
            result.put("userCount", tbUsersService.deleteByIds(userIds));
            Result<String> userResult = sysRegionApi.deleteBatchByIds(IdUtil.getStrIdsByList(userIds));
            if (!Result.CODE.equals(userResult.getCode())) {
                throw new SofnException("删除支撑平台用户失败");
            }
        }
        //删除支撑平台企业
        Result<String> orgResult = sysRegionApi.deleteOrg(compId);
        if (!Result.CODE.equals(orgResult.getCode())) {
            throw new SofnException("删除支撑平台企业失败");
        }
        return result;
    }
}
