package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.OMCompProcessEnum;
import com.sofn.fdpi.enums.OmCompType;
import com.sofn.fdpi.enums.OmSize;
import com.sofn.fdpi.mapper.OmBreedMapper;
import com.sofn.fdpi.mapper.OmCompMapper;
import com.sofn.fdpi.mapper.OmEelImportMapper;
import com.sofn.fdpi.mapper.OmProcMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.util.ExportUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import com.sofn.fdpi.vo.exportBean.ExportOmCompBean;
import com.sofn.fdpi.vo.exportBean.OmBreedProcConvertExportBean;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


@Service("omCompService")
public class OmCompServiceImpl extends BaseService<OmCompMapper, OmComp> implements OmCompService {

    @Resource
    private OmCompMapper omCompMapper;

    @Resource
    private OmFileService omFileService;

    @Resource
    private RegionService regionService;

    @Resource
    private SysRegionApi sysRegionApi;

    @Resource
    private OmEelImportMapper omEelImportMapper;

    @Resource
    private OmBreedMapper omBreedMapper;

    @Resource
    private OmProcMapper omProcMapper;

    @Override
    public OmCompVO getOmComp() {
        SysUserForm sysUserForm = JsonUtils.json2obj(UserUtil.getLoginUserJson(), SysUserForm.class);
        String username = sysUserForm.getUsername();
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("del_flag", BoolUtils.N);
        OmCompVO ocv = OmCompVO.entity2Vo(omCompMapper.selectOne(queryWrapper));
        ocv.setUsername(username);
        ocv.setCompName(sysUserForm.getOrganizationName());
        String provinceCode = sysUserForm.getRegionCode().substring(0, 2) + "0000";
        ocv.setProvinceCode(provinceCode);
        Region region = regionService.getByCode(provinceCode);
        if (Objects.nonNull(region))
            ocv.setProvinceName(region.getRegionName());
        ocv.setCompType(sysUserForm.getRoleNames());
        ocv.setCanAudit(this.canAudit(ocv.getStatus()));
        ocv.setCanApply(this.canApply(ocv.getStatus()));
        ocv.setFiles(omFileService.listBySourceId(ocv.getId()));
        return ocv;
    }

    @Override
    public OmCompVO getOmCompById(String id) {
        OmCompVO ocv = OmCompVO.entity2Vo(this.getById(id));
        ocv.setCanAudit(this.canAudit(ocv.getStatus()));
        ocv.setCanApply(this.canApply(ocv.getStatus()));
        ocv.setFiles(omFileService.listBySourceId(id));
        return ocv;
    }

    @Override
    @Transactional
    public void apply(OmCompForm omCompForm) {
        String id = omCompForm.getId();
        if (StringUtils.hasText(id)) {
            OmComp entity = this.getById(id);
            BeanUtils.copyProperties(omCompForm, entity);
            entity.preUpdate();
            entity.setStatus(OMCompProcessEnum.APPLY.getKey());
            omCompMapper.updateById(entity);
            omFileService.update(id, omCompForm.getFiles());
        } else {
            OmComp entity = new OmComp();
            BeanUtils.copyProperties(omCompForm, entity);
            entity.preInsert();
            entity.setStatus(OMCompProcessEnum.APPLY.getKey());
            id = entity.getCreateUserId();
            omCompMapper.deleteById(id);
            entity.setId(id);
            omCompMapper.insert(entity);
            omFileService.add(entity.getId(), omCompForm.getFiles());
        }
        //????????????
        String oldPassword = omCompForm.getOldPassword();
        String newPassword = omCompForm.getNewPassword();
        if (StringUtils.hasText(oldPassword) && StringUtils.hasText(newPassword)) {
            UpdatePasswordVo vo = new UpdatePasswordVo();
            vo.setId(UserUtil.getLoginUserId());
            vo.setNewPassword(newPassword);
            vo.setOldPassword(oldPassword);
            Result<String> result = sysRegionApi.changePassword(vo);
            if (!Result.CODE.equals(result.getCode())) {
                throw new SofnException(result.getMsg());
            }
        }
    }

    @Override
    public void audit(String id, String status) {
        UpdateWrapper<OmComp> updateWrapper = new UpdateWrapper<>();
        Date now = new Date();
        updateWrapper.eq("id", id).set("status", status).set("audit_time", now).
                set("update_time", now).set("update_user_id", UserUtil.getLoginUserId());
        omCompMapper.update(null, updateWrapper);
    }

    @Override
    public void delete(String id) {
        //????????????????????????????????????
        this.checkCompData(id);
        UpdateWrapper<OmComp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("del_flag", BoolUtils.Y).
                set("update_time", new Date()).set("update_user_id", UserUtil.getLoginUserId());
        omCompMapper.update(null, updateWrapper);
    }

    private void checkCompData(String id) {
        OmComp oc = this.getById(id);
        String compType = oc.getCompType();
        int count = 0;
        if (compType.contains("??????")) {
            QueryWrapper<OmEelImportFrom> importsWrapper = new QueryWrapper<>();
            importsWrapper.eq("del_flag", BoolUtils.N).eq("import_man", id);
            count = omEelImportMapper.selectCount(importsWrapper);
        } else if (compType.contains("??????")) {
            QueryWrapper<OmBreed> breedWrapper = new QueryWrapper<>();
            breedWrapper.eq("del_flag", BoolUtils.N).eq("transfer_comp", id);
            count = omBreedMapper.selectCount(breedWrapper);
        } else if (compType.contains("??????")) {
            QueryWrapper<OmProc> procWrapper = new QueryWrapper<>();
            procWrapper.eq("del_flag", BoolUtils.N).eq("transfer_comp", id);
            count = omProcMapper.selectCount(procWrapper);
        }
        if (count != 0) {
            throw new SofnException("????????????????????????????????????????????????");
        }
    }

    @Override
    public PageUtils<OmCompVO> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<OmComp> list = this.list(params);
        PageInfo<OmComp> omCompPageInfo = new PageInfo<>(list);
        List<OmCompVO> listVo = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            for (OmComp o : list) {
                OmCompVO ocv = OmCompVO.entity2Vo(o);
                ocv.setCanAudit(this.canAudit(ocv.getStatus()));
                ocv.setCanApply(this.canApply(ocv.getStatus()));
                listVo.add(ocv);
            }
            PageInfo<OmCompVO> omCompVOPageInfo = new PageInfo<>(listVo);
            omCompVOPageInfo.setTotal(omCompPageInfo.getTotal());
            omCompVOPageInfo.setPageSize(pageSize);
            omCompVOPageInfo.setPageNum(omCompPageInfo.getPageNum());
            return PageUtils.getPageUtils(omCompVOPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public void exportCount(Map<String, Object> params, HttpServletResponse response) {
        List<OmComp> list = this.list(params);
        if (!CollectionUtils.isEmpty(list)) {
            List<ExportOmCompBean> exportList = new ArrayList<>(list.size());
            list.forEach(o -> {
                ExportOmCompBean eocb = new ExportOmCompBean();
                BeanUtils.copyProperties(o, eocb);
                exportList.add(eocb);
            });
            try {
                ExportUtil.createExcel(ExportOmCompBean.class, exportList, response, "????????????????????????.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }

    @Override
    public List<OmComp> list(Map<String, Object> params) {
        this.perfectParams(params);
        QueryWrapper<OmComp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        queryWrapper.eq(Objects.nonNull(params.get("status")), "status", params.get("status"));
        queryWrapper.eq(Objects.nonNull(params.get("regionCode")), "province_code", params.get("regionCode"));
        queryWrapper.eq(Objects.nonNull(params.get("thirdOrg")), "create_user_id", params.get("thirdOrg"));
        queryWrapper.in(Objects.nonNull(params.get("compType")), "comp_type", (List) params.get("compType"));
        queryWrapper.like(Objects.nonNull(params.get("compName")), "comp_name", params.get("compName"));
        queryWrapper.in(Objects.nonNull(params.get("statuss")), "status", (List) params.get("statuss"));
        Object applyDate = params.get("applyDate");
        if (Objects.nonNull(applyDate)) {
            queryWrapper.ge("update_time", applyDate.toString() + " 00:00:00");
            queryWrapper.le("update_time", applyDate.toString() + " 23:59:59");
        }
        Object orderBy = params.get("orderBy");
        if (Objects.isNull(orderBy)) {
            queryWrapper.orderByDesc("update_time");
        } else {
            Object isAsc = params.get("isAsc");
            queryWrapper.orderBy(true, Objects.isNull(isAsc) ? false : (Boolean) isAsc,
                    orderBy.toString().replaceAll("[A-Z]", "_$0").toLowerCase());
        }
        return omCompMapper.selectList(queryWrapper);
    }


    @Override
    public PageUtils<OmBreedProcTableVo> getConvertList(Map<String, Object> params) {
        this.perfectParams(params);
        if (Objects.nonNull(params.get("pageNo")) && Objects.nonNull(params.get("pageSize"))) {
            //?????????????????????????????????????????????
            PageHelper.offsetPage(Integer.valueOf(params.get("pageNo").toString()), Integer.valueOf(params.get("pageSize").toString()));
        }
        List<OmBreedProcTableVo> omBreedProcTableVos = omCompMapper.getConvertList(params);
        PageInfo<OmBreedProcTableVo> omBreedProcTableVoPageInfo = new PageInfo<>(omBreedProcTableVos);
        return PageUtils.getPageUtils(omBreedProcTableVoPageInfo);
    }

    @Override
    public void exportAll(Map<String, Object> params, HttpServletResponse httpServletResponse) {
        //?????????????????????
        PageUtils<OmBreedProcTableVo> convertList = this.getConvertList(params);
        List<OmBreedProcTableVo> list = convertList.getList();
        //??????????????????
        if ("1".equals(params.get("exportType"))) {
            //?????????????????????
            ArrayList<OmBreedProcConvertExportBean> exportBeans = new ArrayList<>();
            for (OmBreedProcTableVo omBreedProcTableVo : list) {
                OmBreedProcConvertExportBean exportBean = new OmBreedProcConvertExportBean();
                BeanUtils.copyProperties(omBreedProcTableVo, exportBean);
                exportBeans.add(exportBean);
            }
            ExportUtil.createExcel(OmBreedProcConvertExportBean.class, exportBeans, httpServletResponse, "??????????????????.xlsx");
        } else if ("2".equals(params.get("exportType"))) {
            //?????????????????????
            ArrayList<OmBreedProcConvertExportBean> exportBeans = new ArrayList<>();
            for (OmBreedProcTableVo omBreedProcTableVo : list) {
                OmBreedProcConvertExportBean exportBean = new OmBreedProcConvertExportBean();
                BeanUtils.copyProperties(omBreedProcTableVo, exportBean);
                exportBeans.add(exportBean);
            }
            ExportUtil.createExcel(OmBreedProcConvertExportBean.class, exportBeans, httpServletResponse, "??????????????????.xlsx");
        }

    }

    //???????????????????????????????????????
    @Override
    public PageUtils<QuotaListVo> getQuotaList(Map<String, Object> params) {
        //??????????????????
        this.perfectParams(params);
        //???????????????
        PageHelper.offsetPage(Integer.valueOf(params.get("pageNo").toString()), Integer.valueOf(params.get("pageSize").toString()));
        List<QuotaListVo> quotaListVos = null;
        //???????????????????????????
        if (OmCompType.IMPORT.getCompType().equals(params.get("compType"))) {
            //?????????????????????
            quotaListVos = omEelImportMapper.getImportQuotaListByMap(params);
            //?????????????????????
            quotaListVos.stream().forEach(a -> a.setDateType(OmCompType.IMPORT.getCode().toString()));
        } else if (OmCompType.BREED.getCompType().equals(params.get("compType"))) {
            //????????????????????????
            quotaListVos = omBreedMapper.getImportQuotaListByMap(params);
            //?????????????????????
            quotaListVos.stream().forEach(a -> a.setDateType(OmCompType.BREED.getCode().toString()));
        } else if (OmCompType.PROC.getCompType().equals(params.get("compType"))) {
            //?????????????????????
            quotaListVos = omProcMapper.getImportQuotaListByMap(params);
            //?????????????????????
            quotaListVos.stream().forEach(a -> a.setDateType(OmCompType.PROC.getCode().toString()));
        }
        PageInfo<QuotaListVo> quotaListVoPageInfo = new PageInfo<>(quotaListVos);
        return PageUtils.getPageUtils(quotaListVoPageInfo);
    }

//    @Override
//    public PieChartData getPieChartData(String compNameId,String dataId,String dateType) {
//        PieChartData pieChartData = new PieChartData();
//        if (OmCompType.IMPORT.getCode().toString().equals(dateType)) {
//            //?????????????????????????????????
//            QueryWrapper<OmEelImportFrom> wrapper = new QueryWrapper<>();
//            wrapper.eq("del_flag", BoolUtils.N)
//                    .eq("import_man", compName)
//                    .eq("credential", credential)
//                    .select("quantity");
//            OmEelImportFrom one = omEelImportMapper.selectOne(wrapper);
//            //??????????????????
//            pieChartData.setCompType(OmCompType.IMPORT.getCompType());
//            //??????????????????????????????
//            pieChartData.setNum(one.getQuantity());
//            //???????????????????????????????????????
//            pieChartData.setTameAllowTon(omComp.getTameAllowTon());
//        } else if (OmCompType.BREED.getCompType().equals(compType)) {
//            //????????????????????????
//            QueryWrapper<OmBreed> breedWrapper = new QueryWrapper<>();
//            breedWrapper.eq("del_flag", BoolUtils.N)
//                    .eq("transfer_comp", compName)
//                    .eq("credential", credential);
//            OmBreed one = omBreedMapper.selectOne(breedWrapper);
//            //??????????????????
//            pieChartData.setCompType(OmCompType.BREED.getCompType());
//            //??????????????????????????????
//            pieChartData.setNum(one.getDealNum());
//            //???????????????????????????????????????
//            pieChartData.setTameAllowTon(omComp.getTameAllowTon());
//        } else if (OmCompType.PROC.getCompType().equals(compType)) {
//            //????????????????????????
//            QueryWrapper<OmProc> procWrapper = new QueryWrapper<>();
//            procWrapper.eq("del_flag", BoolUtils.N)
//                    .eq("transfer_comp", compName)
//                    .eq("credential", credential);
//            OmProc one = omProcMapper.selectOne(procWrapper);
//            //??????????????????
//            pieChartData.setCompType(OmCompType.BREED.getCompType());
//            //??????????????????????????????
//            pieChartData.setNum(one.getDealNum());
//            //???????????????????????????????????????
//            pieChartData.setTameAllowTon(omComp.getTameAllowTon());
//        }
//        return pieChartData;
//    }

    @Override
    public List<SelectVo> listComp(ArrayList<Integer> compTypes) {
        Map<String, Object> params = Maps.newHashMap();
        //??????????????????????????????
        ArrayList<String> compTypess = new ArrayList<>();
        for (Integer compType : compTypes) {
            compTypess.add(OmCompType.getCompTypeByCode(compType));
        }
        //?????????????????????
        params.put("compType", compTypess);
        List<OmComp> list = this.list(params);
        if (!CollectionUtils.isEmpty(list)) {
            List<SelectVo> result = Lists.newArrayListWithCapacity(list.size());
            for (OmComp oc : list) {
                result.add(new SelectVo(oc.getId(), oc.getCompName()));
            }
            return result.stream().sorted(Comparator.comparing(SelectVo::getVal)).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    private void perfectParams(Map<String, Object> params) {
//        OrganizationInfo oif = SysOwnOrgUtil.getOrgInfo();
//        String organizationLevel = oif.getOrganizationLevel();
//        if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
//            params.put("regionCode", oif.getRegionLastCode().substring(0, 2) + "0000");
//        }
    }

    private Boolean canApply(String status) {
        OrganizationInfo oif = SysOwnOrgUtil.getOrgInfo();
        return BoolUtils.N.equals(oif.getThirdOrg()) && OMCompProcessEnum.UN_PASS.getKey().equals(status) ? true : false;
    }

    private Boolean canAudit(String status) {
        OrganizationInfo oif = SysOwnOrgUtil.getOrgInfo();
        return BoolUtils.Y.equals(oif.getThirdOrg()) && OMCompProcessEnum.APPLY.getKey().equals(status) ? true : false;
    }
}

