package com.sofn.agsjsi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjsi.enums.ApproveLevelEnum;
import com.sofn.agsjsi.enums.ProcessStatusEnum;
import com.sofn.agsjsi.enums.RedisCacheKeyEnum;
import com.sofn.agsjsi.mapper.WetExamplePointCollectMapper;
import com.sofn.agsjsi.mapper.WetExamplePointCollectProcMapper;
import com.sofn.agsjsi.model.WetExamplePointCollect;
import com.sofn.agsjsi.model.WetExamplePointCollectProc;
import com.sofn.agsjsi.service.WetExamplePointCollectService;
import com.sofn.agsjsi.util.ExportUtil;
import com.sofn.agsjsi.util.SysOwnOrgUtil;
import com.sofn.agsjsi.vo.*;
import com.sofn.agsjsi.vo.excelBean.WetExamplePointCollectExcel;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("wetExamplePointCollectService")
public class WetExamplePointCollectServiceImpl extends BaseService<WetExamplePointCollectMapper, WetExamplePointCollect> implements WetExamplePointCollectService {
    @Autowired
    private WetExamplePointCollectMapper collectMapper;
    @Autowired
    private WetExamplePointCollectProcMapper procMapper;
    @Autowired
    private RedisHelper redisHelper;
    private String redisKey = RedisCacheKeyEnum.POINT_COLLECT_KEY.getCode();
    private final String redisKeyByCode=this.redisKey+"_BY_CODE";

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    /**
     * ????????????
     * WuXY
     * 2020-4-10 11:26:47
     *
     * @param wetName     ???????????????
     * @param wetCode     ???????????????
     * @param secondBasin ??????????????????
     * @param status      ??????
     * @param startTime   ????????????
     * @param endTime     ????????????
     * @param pageNo      ????????????
     * @param pageSize    ????????????
     * @return ??????????????????
     */
    @Override
    public PageUtils<WetExamplePointCollectVo> listPage(String wetName, String wetCode, String secondBasin, String status, String startTime, String endTime, int pageNo, int pageSize, String isApprove) {
        Result<Map<String, Object>> whereMapResult = SysOwnOrgUtil.getDataWhereMapForDifferenceLevel();
        if (Result.CODE.equals(whereMapResult.getCode())) {
            Map<String, Object> data = whereMapResult.getData();
            PageHelper.offsetPage(pageNo, pageSize);
            List<WetExamplePointCollectVo> list = collectMapper.listForCondition(wetName, wetCode, secondBasin, status, startTime, endTime
                    , data.get("sysOrgProvince") == null ? "" : data.get("sysOrgProvince").toString()
                    , data.get("sysOrgCity") == null ? "" : data.get("sysOrgCity").toString()
                    , data.get("sysOrgDistrict") == null ? "" : data.get("sysOrgDistrict").toString()
                    , data.get("sysUserLevel") == null ? "" : data.get("sysUserLevel").toString()
                    , isApprove);
            PageInfo<WetExamplePointCollectVo> pageInfo = new PageInfo<>(list);
            return PageUtils.getPageUtils(pageInfo);
        } else {
            return null;
        }
    }

    /**
     * ??????id??????????????????
     * WuXY
     * 2020-4-10 11:28:44
     *
     * @param id     ??????
     * @param isView true??????????????????false??????????????????
     * @return ????????????
     */
    @Override
    public WetExamplePointCollectVo getPointCollect(String id, boolean isView) {
        WetExamplePointCollectVo registerVo = collectMapper.getObj(id);
        if (isView && registerVo != null) {
            List<ProcessVo> processList = procMapper.listByForeignId(id);
            registerVo.setProcessList(processList);
        }
        return registerVo;
    }

    /**
     * ???????????????
     * WuXY
     * 2020-4-10 11:28:44
     *
     * @param form ??????
     * @return '1':?????????????????????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveAndReport(WetExamplePointCollectForm form, boolean isReport) {
        WetExamplePointCollect collect = getWetExamplePointCollectWithAssemble(form, isReport ? 2 : 1);
        try {
            collectMapper.insert(collect);
            if (isReport) {
                WetExamplePointCollectProc process = getProcWithAssemble(collect.getId(), ProcessStatusEnum.STATUS_REPORT.getDefaultAdvice(), collect.getStatus(), collect.getCreateUserId(), collect.getCreateUserName(), collect.getCreateTime());
                procMapper.insert(process);
            }

        } catch (Exception ex) {
            log.error("???????????????????????????????????????" + ex.getMessage());
            throw new SofnException("???????????????");
        }
        return "1";
    }

    /**
     * ???????????????
     * WuXY
     * 2020-4-10 11:28:44
     *
     * @param form ??????
     * @return '1':?????????????????????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateAndReport(WetExamplePointCollectForm form, boolean isReport) {

        if (StringUtils.isBlank(form.getId())) {
            return "?????????id???";
        }
        WetExamplePointCollect collect = getWetExamplePointCollectWithAssemble(form, isReport ? 4 : 3);
        try {
            collectMapper.updateById(collect);
            if (isReport) {
                WetExamplePointCollectProc process = getProcWithAssemble(collect.getId(), ProcessStatusEnum.STATUS_REPORT.getDefaultAdvice(), collect.getStatus(), collect.getUpdateUserId(), collect.getUpdateUserName(), collect.getUpdateTime());
                procMapper.insert(process);
            }
        } catch (Exception ex) {
            log.error("?????????????????????????????????????????????" + ex.getMessage());
            throw new SofnException("???????????????");
        }
        return "1";
    }

    /**
     * ??????/??????
     * WXY
     * 2020-2-29 10:02:44
     *
     * @param id   ??????
     * @param type 1????????????2?????????
     * @return "1":??????;???????????????
     */
    @Override
    @Transactional
    public String updateStatus(String id, String type) {
        //??????????????????????????????????????????????????????
        WetExamplePointCollect oneById = this.getOneById(id);
        if (oneById == null) {
            return "Id????????????";
        }
        if ("2".equals(type)) {
            //0????????????1????????????2????????????3??????????????????4??????????????????5??????????????????6??????????????????7??????????????????8??????????????????9???????????????
            if ("0".equals(oneById.getStatus())) {
                return "???????????????????????????????????????";
            } else if ("1".equals(oneById.getStatus())) {
                return "?????????????????????????????????????????????";
            } else if ("3,4,5,6,7,8".indexOf(oneById.getStatus()) > 0) {
                return "????????????????????????????????????";
            }

        } else {
            //??????
            if ("2".equals(oneById.getStatus())) {
                return "?????????????????????????????????";
            } else if ("3,4,5,6,7,8,9".indexOf(oneById.getStatus()) > 0) {
                return "?????????????????????????????????";
            }
        }
        //??????????????????
        String status = "1".equals(type) ? ProcessStatusEnum.STATUS_REPORT.getStatus() : ProcessStatusEnum.STATUS_RECALL.getStatus();
        WetExamplePointCollect collect = getWetExamplePointCollectForUpdateStatus(id, status);
        //??????????????????????????? ????????????
        String advice = "1".equals(type) ? ProcessStatusEnum.STATUS_REPORT.getDefaultAdvice() : ProcessStatusEnum.STATUS_RECALL.getDefaultAdvice();
        WetExamplePointCollectProc process = getProcWithAssemble(id, advice, status, collect.getUpdateUserId(), collect.getUpdateUserName(), collect.getUpdateTime());
        try {
            boolean isSuccess = this.updateById(collect);
            if (isSuccess) {
                //???????????????????????????
                procMapper.insert(process);
            }
            return "1";
        } catch (Exception ex) {
            log.error("???????????????/???????????????" + ex.getMessage());
            throw new SofnException("??????!");
        }
    }

    /**
     * ??????
     * WXY
     * 2020-2-29 10:02:44
     *
     * @param id ??????
     * @return "1":??????;???????????????
     */
    @Override
    public String delObj(String id) {
        WetExamplePointCollect collect = new WetExamplePointCollect();
        collect.setId(id);
        collect.setDelFlag("Y");
        collect.setUpdateTime(new Date());
        collect.setUpdateUserName(SysOwnOrgUtil.getUserNickName());
        collectMapper.updateById(collect);
        return "1";
    }

    /**
     * ??????????????????
     *
     * @param id        ??????
     * @param isApprove true????????????false?????????
     * @return ????????????
     */
    @Override
    public String approveOrReturn(String id, String advice, boolean isApprove) {
        //???????????????????????????
        WetExamplePointCollect collect = new WetExamplePointCollect();
        collect.setId(id);
        collect.preUpdate();
        //?????????????????????????????????????????????????????????
        Result<SysOrgAndRegionVo> levelResult = SysOwnOrgUtil.getSysApproveLevelForApprove();
        if (Result.CODE.equals(levelResult.getCode())) {
            SysOrgAndRegionVo levelModel = levelResult.getData();
            if (ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel().equals(levelModel.getApproveLevel())) {
                return "??????????????????";
            } else if (ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel().equals(levelModel.getApproveLevel())) {
                //???
                collect.setStatus(isApprove ? ProcessStatusEnum.STATUS_APPROVE_FIRST.getStatus() : ProcessStatusEnum.STATUS_RETURN_FIRST.getStatus());
            } else if (ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel().equals(levelModel.getApproveLevel())) {
                //???
                collect.setStatus(isApprove ? ProcessStatusEnum.STATUS_APPROVE_SECOND.getStatus() : ProcessStatusEnum.STATUS_RETURN_SECOND.getStatus());
            } else if (ApproveLevelEnum.APPROVE_THREE_LEVEL.getLevel().equals(levelModel.getApproveLevel())) {
                //??????
                collect.setStatus(isApprove ? ProcessStatusEnum.STATUS_APPROVE_THREE.getStatus() : ProcessStatusEnum.STATUS_RETURN_THREE.getStatus());
            } else if (ApproveLevelEnum.APPROVE_FOUR_LEVEL.getLevel().equals(levelModel.getApproveLevel())) {
                //??????
                collect.setStatus(ProcessStatusEnum.STATUS_APPROVE_EXPORT.getStatus());
            }

        } else {
            return levelResult.getMsg();
        }
        //??????????????????
        WetExamplePointCollectProc proc = getProcWithAssemble(collect.getId(), advice, collect.getStatus(), collect.getUpdateUserId(), SysOwnOrgUtil.getUserNickName(), collect.getUpdateTime());
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            collectMapper.updateById(collect);
            procMapper.insert(proc);
            platformTransactionManager.commit(status);
            if (ProcessStatusEnum.STATUS_APPROVE_EXPORT.getStatus().equals(collect.getStatus())) {
                WetExamplePointCollect oneInDB = this.getOneById(id);
                //??????????????????
                if(StringUtils.isNotBlank(oneInDB.getProvinceCode())){
                    redisHelper.hdel(redisKeyByCode,oneInDB.getProvinceCode());
                }
                if(StringUtils.isNotBlank(oneInDB.getCityCode())){
                    redisHelper.hdel(redisKeyByCode,oneInDB.getCityCode());
                }
                if(StringUtils.isNotBlank(oneInDB.getAreaCode())){
                    redisHelper.hdel(redisKeyByCode,oneInDB.getAreaCode());
                }
                redisHelper.del(redisKey);
            }
        } catch (Exception ex) {
            log.error("?????????????????????????????????/????????????" + ex.getMessage());
            platformTransactionManager.rollback(status);
            return isApprove ? "???????????????" : "???????????????";
        }
        return "1";
    }

    /**
     * ??????excel
     * WuXY
     * 2020-4-10 11:26:47
     *
     * @param wetName     ???????????????
     * @param wetCode     ???????????????
     * @param secondBasin ??????????????????
     * @param status      ??????
     * @param startTime   ????????????
     * @param endTime     ????????????
     */
    @Override
    public void export(String wetName, String wetCode, String secondBasin, String status, String startTime, String endTime, String isApprove, HttpServletResponse response) {
        //????????????excel?????????
        Result<Map<String, Object>> whereMapResult = SysOwnOrgUtil.getDataWhereMapForDifferenceLevel();
        List<WetExamplePointCollectExcel> list = null;
        if (Result.CODE.equals(whereMapResult.getCode())) {
            Map<String, Object> data = whereMapResult.getData();
            list = collectMapper.listForExport(wetName, wetCode, secondBasin, status, startTime, endTime
                    , data.get("sysOrgProvince") == null ? "" : data.get("sysOrgProvince").toString()
                    , data.get("sysOrgCity") == null ? "" : data.get("sysOrgCity").toString()
                    , data.get("sysOrgDistrict") == null ? "" : data.get("sysOrgDistrict").toString()
                    , data.get("sysUserLevel") == null ? "" : data.get("sysUserLevel").toString()
                    , isApprove);
        }
        //??????excel
        ExportUtil.createExcel(WetExamplePointCollectExcel.class, list, response, "???????????????????????????.xlsx");
    }

    @Override
    public List<DropDownVo> listForSelect(String lastRegionCode) {
        List<DropDownVo> list = null;

        Object resultObj=this.getRedisData(lastRegionCode);
        if (resultObj != null) {
            list = JsonUtils.json2List(resultObj.toString(), DropDownVo.class);
        } else {
            synchronized (this) {
                resultObj = this.getRedisData(lastRegionCode);
                if (resultObj != null) {
                    list = JsonUtils.json2List(resultObj.toString(), DropDownVo.class);
                } else {
                    list = collectMapper.listForSelect(lastRegionCode);
                    if(StringUtils.isBlank(lastRegionCode)){
                        redisHelper.set(this.redisKey, JsonUtils.obj2json(list));
                    }else{
                        redisHelper.hset(this.redisKeyByCode,lastRegionCode,JsonUtils.obj2json(list));
                    }
                }
            }
        }
        return list;
    }

    /**
     * ???redis???????????????
     * @param lastRegionCode ??????code
     * @return object
     */
    private Object getRedisData(String lastRegionCode){
        Object resultObj=null;
        if(StringUtils.isBlank(lastRegionCode)){
            resultObj = redisHelper.get(this.redisKey);
        }else{
            resultObj=redisHelper.hget(this.redisKeyByCode,lastRegionCode);
        }
        return resultObj;
    }

    /**
     * ???????????????????????????????????????????????????
     *
     * @param registerForm   ??????
     * @param controllerType 1????????????2?????????????????????3????????????4??????????????????
     * @return SoilArea
     */
    private WetExamplePointCollect getWetExamplePointCollectWithAssemble(WetExamplePointCollectForm registerForm, int controllerType) {
        WetExamplePointCollect collect = registerForm.getWetExamplePointCollect(registerForm);
        //????????????????????????
        String userNickName = SysOwnOrgUtil.getUserNickName();
        if (controllerType == 1 || controllerType == 2) {
            collect.preInsert();
            collect.setCreateUserName(userNickName);
            collect.setUpdateUserName(userNickName);
            //???????????????????????????
            List<String> sysRegionList = SysOwnOrgUtil.getCurrentUserOrgRegion();
            if (!CollectionUtils.isEmpty(sysRegionList)) {
                for (int i = 0; i < sysRegionList.size(); i++) {
                    switch (i) {
                        case 0:
                            collect.setOrgProvinceCode(sysRegionList.get(i));
                            break;
                        case 1:
                            collect.setOrgCityCode(sysRegionList.get(i));
                            break;
                        case 2:
                            collect.setOrgAreaCode(sysRegionList.get(i));
                            break;
                    }
                }
            }
        }
        if (controllerType == 3 || controllerType == 4) {
            collect.preUpdate();
            collect.setUpdateUserName(userNickName);
        }
        collect.setStatus(controllerType == 1 || controllerType == 3 ? "0" : "2");
        return collect;
    }


    /**
     * ???????????????????????????????????????????????????
     *
     * @param collectId  ??????id
     * @param advice     ??????
     * @param status     ??????
     * @param personId   ???id
     * @param personName ???name
     * @param createTime ????????????
     * @return WetExamplePointCollectProc ???????????????
     */
    private WetExamplePointCollectProc getProcWithAssemble(String collectId, String advice, String status, String personId, String personName, Date createTime) {
        WetExamplePointCollectProc proc = new WetExamplePointCollectProc();
        proc.setId(IdUtil.getUUId());
        proc.setCollectId(collectId);
        //?????????0????????????1????????????2????????????3??????????????????4??????????????????5??????????????????6??????????????????7??????????????????8??????????????????9???????????????
        switch (status) {
            case "1":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_RECALL.getDefaultAdvice() : advice);
                break;
            case "2":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_REPORT.getDefaultAdvice() : advice);
                break;
            case "3":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_RETURN_FIRST.getDefaultAdvice() : advice);
                break;
            case "4":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_APPROVE_FIRST.getDefaultAdvice() : advice);
                break;
            case "5":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_RETURN_SECOND.getDefaultAdvice() : advice);
                break;
            case "6":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_APPROVE_SECOND.getDefaultAdvice() : advice);
                break;
            case "7":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_RETURN_THREE.getDefaultAdvice() : advice);
                break;
            case "8":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_APPROVE_THREE.getDefaultAdvice() : advice);
                break;
            case "9":
                proc.setAdvice(StringUtils.isBlank(advice) ? ProcessStatusEnum.STATUS_APPROVE_EXPORT.getDefaultAdvice() : advice);
                break;
        }
        proc.setPersonId(personId);
        proc.setPersonName(personName);
        proc.setCreateTime(createTime);
        proc.setStatus(status);
        return proc;
    }

    /**
     * ???????????????????????????
     *
     * @param id     ??????
     * @param status ??????
     * @return ?????????????????????
     */
    private WetExamplePointCollect getWetExamplePointCollectForUpdateStatus(String id, String status) {
        WetExamplePointCollect collect = new WetExamplePointCollect();
        collect.setId(id);
        collect.preUpdate();
        collect.setUpdateUserName(SysOwnOrgUtil.getUserNickName());
        collect.setStatus(status);
        return collect;
    }

}
