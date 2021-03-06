package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.enums.DepartmentRedisKeyEnum;
import com.sofn.fdpi.enums.DepartmentTypeEnum;
import com.sofn.fdpi.mapper.TbCompMapper;
import com.sofn.fdpi.mapper.TbDepartmentMapper;
import com.sofn.fdpi.model.TbComp;
import com.sofn.fdpi.model.TbDepartment;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service("tbDepartmentService")
public class TbDepartmentServiceImpl extends BaseService<TbDepartmentMapper, TbDepartment> implements TbDepartmentService {

    @Resource
    private TbDepartmentMapper tbDepartmentMapper;

    @Resource
    private TbCompMapper tbCompMapper;

    @Resource
    private RedisHelper redisHelper;

    @Override
    public PageUtils<TbDepartmentVo> listForPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Result<Map<String, Object>> whereResult = SysOwnOrgUtil.getDataWhereMapForDifferenceLevel(false);
        if (Result.CODE.equals(whereResult.getCode())) {
            map.putAll(whereResult.getData());
        }
        List<TbDepartmentVo> listDept = tbDepartmentMapper.ListByCondition(map);
        for (TbDepartmentVo tdv : listDept) {
            tdv.setTypeName(DepartmentTypeEnum.getVal(tdv.getType()));
        }
        PageInfo<TbDepartmentVo> pageInfo = new PageInfo<>(listDept);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional
    public void addDepartment(TbDepartmentForm tbDepartmentForm, String types) {
        //??????????????????
        RedisUserUtil.validReSubmit("fdpi_department_add");
        List<String> typeList = IdUtil.getIdsByStr(types);
        if (!CollectionUtils.isEmpty(typeList)) {
            for (String type : typeList) {
                //??????????????????????????????
                QueryWrapper<TbDepartment> query = new QueryWrapper<>();
                query.eq("DEPT_PRO", tbDepartmentForm.getDeptPro());
                if (StringUtils.isNotBlank(tbDepartmentForm.getDeptCity())) {
                    query.eq("DEPT_CITY", tbDepartmentForm.getDeptCity());
                } else {
                    query.isNull("DEPT_CITY");
                }
                if (StringUtils.isNotBlank(tbDepartmentForm.getDeptArea())) {
                    query.eq("DEPT_AREA", tbDepartmentForm.getDeptArea());
                } else {
                    query.isNull("DEPT_AREA");
                }
                query.eq("DEL_FLAG", "N");
                query.eq("TYPE", StringUtils.isBlank(type) ? "1" : type);
                int count = this.count(query);
                if (count > 0) {
                    throw new SofnException("?????????????????????????????????????????????");
                }

                Map<String, Object> map = Maps.newHashMap();
                map.put("deptName", tbDepartmentForm.getDeptName());
                map.put("orgId", tbDepartmentForm.getOrgId());
                map.put("type", type);
                TbDepartmentVo tbDeptByOne = tbDepartmentMapper.getOneForCheckRepeat(map);
                boolean isAdd = true;
                if (tbDeptByOne != null) {
                    if ("N".equals(tbDeptByOne.getDelFlag())) {
                        throw new SofnException("????????????????????????");
                    } else {
                        //??????????????????????????????
                        isAdd = false;
                    }
                }

                TbDepartment tbDepartment = tbDepartmentForm.getTbDepartment(tbDepartmentForm);
                tbDepartment.setType(type);
                int result = 0;
                if (isAdd) {
                    tbDepartment.preInsert();
                    result = tbDepartmentMapper.insert(tbDepartment);
                } else {
                    tbDepartment.preUpdate();
                    tbDepartment.setId(tbDeptByOne.getId());
                    tbDepartment.setDelFlag("N");
                    result = tbDepartmentMapper.updateById(tbDepartment);
                }

                if (result > 0) {
                    //???????????????????????????redis??????
                    redisHelper.hset(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), type + "_" + tbDepartment.getOrgId(), JsonUtils.obj2json(tbDepartment));
                }
            }
        } else {
            throw new SofnException("????????????????????????????????????");
        }
    }

    @Override
    public TbDepartmentVo getOneById(String deptId, String type) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", deptId);
        map.put("type", type);
        return tbDepartmentMapper.getOneById(map);
    }

    @Override
    public String deleteDepartment(String deptId) {
        TbDepartment oneById = this.getOneById(deptId);
        if (deptId == null) {
            return "???????????????????????????";
        }
        if (oneById == null) {
            return "????????????????????????!";
        }
        //???????????????????????????????????????????????????????????????????????????
        QueryWrapper<TbComp> compQuery = new QueryWrapper<>();
        compQuery.eq("DIRECLY_ID", oneById.getOrgId());
        compQuery.eq("DEL_FLAG", "N");
        int compCount = tbCompMapper.selectCount(compQuery);
        if (compCount > 0) {
            return "???????????????????????????????????????????????????";
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("deptId", deptId);
        map.put("updateTime", new Date());
        map.put("updateUserId", UserUtil.getLoginUserId());
        int result = tbDepartmentMapper.deleteDepartment(map);
        if (result > 0) {
            //?????????????????????redis????????????
            redisHelper.hdel(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), oneById.getType() + "_" + oneById.getOrgId());
            return "1";
        }
        return "???????????????";
    }

    @Override
    public String updateDepartment(TbDepartmentForm tbDepartmentForm, String type) {
        //??????????????????
        RedisUserUtil.validReSubmit("fdpi_department_update");
        //???????????????
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("id", tbDepartmentForm.getId());
        queryMap.put("deptName", tbDepartmentForm.getDeptName());
        queryMap.put("orgId", tbDepartmentForm.getOrgId());
        queryMap.put("type", type);
        queryMap.put("delFlag", "N");
        TbDepartmentVo TbDepartmentVo = tbDepartmentMapper.getOneForCheckRepeat(queryMap);
        if (TbDepartmentVo != null) {
            return "?????????????????????";
        }
        TbDepartment tbDepartment = tbDepartmentForm.getTbDepartment(tbDepartmentForm);
        tbDepartment.preUpdate();
        int updateResult = tbDepartmentMapper.updateById(tbDepartment);
        if (updateResult > 0) {
            //???????????????????????????redis??????
            redisHelper.hset(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), type + "_" + tbDepartment.getOrgId(), JsonUtils.obj2json(tbDepartment));
            return "1";
        }
        return "???????????????";
    }

    @Override
    public String convertDataToRedis() {
        List<TbDepartmentVo> list = tbDepartmentMapper.ListByCondition(Maps.newHashMap());
        for (TbDepartmentVo tbDepartmentVo : list) {
            redisHelper.hset(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), tbDepartmentVo.getType() + "_" + tbDepartmentVo.getOrgId(), JsonUtils.obj2json(tbDepartmentVo));
        }
        return "1";
    }


    /**
     * ??????????????????????????????????????????????????????
     *
     * @param compProvinceCode ????????????
     * @param compCityCode     ????????????
     * @param compDistrictCode ????????????
     * @return ???????????????????????????
     */
    @Override
    public DepartmentLevelVo getRedirectTempId(String compProvinceCode, String compCityCode, String compDistrictCode) {
        DepartmentLevelVo deptLevelModel = new DepartmentLevelVo();
        TbDepartment deptModel;
        for (int i = 0; i < 3; i++) {
            QueryWrapper<TbDepartment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("DEL_FLAG", "N");
            //1:????????????(?????????????????????)???2??????????????????????????????
            queryWrapper.eq("TYPE", "1");
            switch (i) {
                case 0:
                    queryWrapper.eq("DEPT_AREA", compDistrictCode);
                    break;
                case 1:
                    queryWrapper.eq("DEPT_CITY", compCityCode);
                    queryWrapper.isNull("DEPT_AREA");
                    break;
                case 2:
                    queryWrapper.eq("DEPT_PRO", compProvinceCode);
                    queryWrapper.isNull("DEPT_CITY");
                    queryWrapper.isNull("DEPT_AREA");
                    break;
            }
            deptModel = this.getOne(queryWrapper);
            if (deptModel != null) {
                deptLevelModel.setSysDeptId(deptModel.getOrgId());
                break;
            }
        }
        return deptLevelModel;
    }

    @Override
    public Boolean isDirectlyDept() {
        return this.isAuth(DepartmentTypeEnum.PAPER_BINDING.getKey());
    }

    @Override
    public Boolean isAuth(String type) {
        OrganizationInfo of = SysOwnOrgUtil.getOrgInfo();
        if (BoolUtils.N.equals(of.getThirdOrg())) {
            return false;
        } else {
            String organizationLevel = of.getOrganizationLevel();
            if ("province".equals(organizationLevel)) {
                return true;
            } else if ("city".equals(organizationLevel) || "county".equals(organizationLevel)) {
                QueryWrapper<TbDepartment> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("DEL_FLAG", BoolUtils.N);
                switch (organizationLevel) {
                    case "city":
                        queryWrapper.eq("type", type).eq("dept_city", of.getRegionLastCode());
                        queryWrapper.isNull("dept_area");
                        break;
                    case "county":
                        queryWrapper.eq("type", type).eq("dept_area", of.getRegionLastCode());
                        break;
                }
                return tbDepartmentMapper.selectCount(queryWrapper) != 0;
            } else {
                return false;
            }
        }
    }

    @Override
    public Set<String> listAllAuth(String type) {
        Set<String> result = new HashSet<>();
        OrganizationInfo of = SysOwnOrgUtil.getOrgInfo();
        if (BoolUtils.N.equals(of.getThirdOrg())) {
            return result;
        } else {
            QueryWrapper<TbDepartment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("DEL_FLAG", BoolUtils.N).eq("type", type).
                    and(warpper -> warpper.eq("dept_pro", of.getRegionLastCode()).
                            or().eq("dept_city", of.getRegionLastCode()).
                            or().eq("dept_area", of.getRegionLastCode()));
            queryWrapper.select("dept_pro", "dept_city", "dept_area");
            List<TbDepartment> tbDepartments = tbDepartmentMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(tbDepartments)) {
                //??????????????????????????????????????????
                if (DepartmentTypeEnum.PAPER_RECORD.getKey().equals(type) ||
                        DepartmentTypeEnum.PAPER_PRINT.getKey().equals(type)) {
                    for (TbDepartment tbDepartment : tbDepartments) {
                        String deptCity = tbDepartment.getDeptCity();
                        String deptArea = tbDepartment.getDeptArea();
                        if (StringUtils.isNotBlank(deptArea) && StringUtils.isNotBlank(deptCity)) {
                            result.add(deptArea);
                        } else if (StringUtils.isNotBlank(deptCity)) {
                            result.add(deptCity);
                        }
                    }
                } else {
                    for (TbDepartment tbDepartment : tbDepartments) {
                        if (StringUtils.isNotBlank(tbDepartment.getDeptPro()))
                            result.add(tbDepartment.getDeptPro());
                        if (StringUtils.isNotBlank(tbDepartment.getDeptCity()))
                            result.add(tbDepartment.getDeptCity());
                        if (StringUtils.isNotBlank(tbDepartment.getDeptArea()))
                            result.add(tbDepartment.getDeptArea());
                    }
                }
            }
        }
        return result;
    }
}
