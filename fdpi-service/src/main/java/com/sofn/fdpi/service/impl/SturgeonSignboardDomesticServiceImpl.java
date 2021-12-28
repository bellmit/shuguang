package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.CitesEnum;
import com.sofn.fdpi.mapper.SturgeonSignboardDomesticMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.SturgeonService;
import com.sofn.fdpi.service.SturgeonSignboardDomesticService;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.util.CodeUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service(value = "sturgeonSignboardDomesticService")
public class SturgeonSignboardDomesticServiceImpl extends BaseService<SturgeonSignboardDomesticMapper, SturgeonSignboardDomestic> implements SturgeonSignboardDomesticService {

    @Resource
    private SturgeonSignboardDomesticMapper ssdMapper;
    @Resource
    @Lazy
    private TbCompService tbCompService;
    @Resource
    @Lazy
    private SturgeonService sturgeonService;


    @Override
    @Transactional
    public String savaBatch(Sturgeon sturgeon, List<SturgeonSubVo> sturgeonSubVos, String thirdPrint) {
        String compId = sturgeon.getCompId();
        TbCompVo tcv = tbCompService.getCombById(compId);
        String provinceCode = CodeUtil.getProvinceCode(tcv.getRegionInCh().split("-")[0]);
        String compCode = tcv.getCompCode();
        String year = DateUtils.format(new Date(), "yyyy").substring(2);
        List<SturgeonSignboardDomestic> ssd = Lists.newArrayList();
        //生成A B 标签
        for (SturgeonSubVo ssv : sturgeonSubVos) {
            //00.杂交鲟 01.施氏鲟 02.俄罗斯鲟 03.西伯利亚鲟 04.闪光鲟 05.鳇 06.欧洲鳇 07.裸腹鲟 08.小体鲟 09.高首鳇 10.长江鲟
            String speType = ssv.getVariety();
            Integer startNum = Integer.parseInt(ssv.getStartNum());
            Integer endNum = Integer.parseInt(ssv.getEndNum());
            Integer applyNum = endNum - startNum + 1;
            List<String> oldSignboards = this.listOldSignboards(provinceCode, year, compCode, speType, ssv.getLabel());
            for (int i = 0; i < applyNum; i++) {
                ssd.add(this.generateSturgeonSignboardAB(oldSignboards, ssv, thirdPrint, compId, provinceCode,
                        year, compCode, speType, startNum++));
            }
        }
        //生成C 标签
        sturgeonSubVos = SturgeonServiceImpl.mergeLabelS(sturgeonSubVos);
        for (SturgeonSubVo ssv : sturgeonSubVos) {
            List<String> oldSignboards = this.listOldSignboards(provinceCode, year, compCode, ssv.getVariety(), ssv.getLabel());
            String speType = ssv.getVariety();
            ssd.add(this.generateSturgeonSignboardC(oldSignboards, ssv, thirdPrint, compId, provinceCode, year,
                    compCode, "S", speType));
        }
        this.saveBatch(ssd);
        return ssd.get(0).getSignboard().substring(0, 12);
    }

    @Override
    public void validSignboard(SturgeonSub entity) {
        String year = DateUtils.format(new Date(), "yyyy").substring(2);
        TbCompVo tcv = tbCompService.getCombById(UserUtil.getLoginUserOrganizationId());
        String provinceCode = CodeUtil.getProvinceCode(tcv.getRegionInCh().split("-")[0]);
        String compCode = tcv.getCompCode();
        String speType = entity.getVariety();
        List<String> oldSignboards = this.listOldSignboards(provinceCode, year, tcv.getCompCode(), speType, null);
        Integer startNum = Integer.parseInt(entity.getStartNum());
        Integer endNum = Integer.parseInt(entity.getEndNum());
        Integer applyNum = endNum - startNum + 1;
        for (int i = 0; i < applyNum; i++) {
            String signboard = new StringBuilder("0000").append(provinceCode).
                    append(year).append(compCode).append(speType).append(String.format("%08d", startNum++)).toString();
            if (oldSignboards.contains(signboard)) {
                throw new SofnException("品种（" + CitesEnum.getVal(entity.getVariety()) + "）今年已使用或正在申请号段" +
                        signboard + ",不可再使用");
            } else {
                oldSignboards.add(signboard);
            }


        }


    }


    private List<String> listOldSignboards(String provinceCode, String year, String compCode, String speType, String label) {
        QueryWrapper<SturgeonSignboardDomestic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).likeRight("signboard", new StringBuilder("0000").
                append(provinceCode).append(year).append(compCode).append(speType));
        if ("S".equals(label)) {
            queryWrapper.eq("label", "S");
        } else {
            queryWrapper.and(wrapper -> wrapper.eq("label", "A")
                    .or().eq("label", "B"));
        }
        return ssdMapper.selectList(queryWrapper).stream().map(SturgeonSignboardDomestic::getSignboard).collect(Collectors.toList());
    }

    private SturgeonSignboardDomestic generateSturgeonSignboardC(
            List<String> oldSignboards, SturgeonSubVo ssv, String thirdPrint, String compId, String provinceCode,
            String year, String compCode, String label, String speType) {
        SturgeonSignboardDomestic entity = new SturgeonSignboardDomestic();
        entity.preInsert();
        entity.setCompId(compId);
        entity.setSturgeonSubId(ssv.getId());
        entity.setCaseNum(ssv.getCaseNum());
        entity.setPrintSum(0);
        entity.setThirdPrint(thirdPrint);
        entity.setLabel(label);
        entity.setStickerPrintStatus(BoolUtils.N);
        //编码
        String signboard = new StringBuilder("0000").append(provinceCode).append(year).append(compCode).append(speType).
                append(String.format("%08d", Integer.parseInt(ssv.getStartNum()))).append("-").
                append(String.format("%08d", Integer.parseInt(ssv.getEndNum()))).toString();
        if (oldSignboards.contains(signboard)) {
            throw new SofnException("标识编码（" + signboard + ")已存在，请检查");
        }
        entity.setSignboard(signboard);
        return entity;
    }

    /**
     * 生成标识编码
     * 国内鱼子酱标识代码规则： 物种代码（4位：只能是0000）+省份（2位）+年份（2位）+企业代码（4位）+物种类型（2位）+号段（8位）
     */
    private SturgeonSignboardDomestic generateSturgeonSignboardAB(
            List<String> oldSignboards, SturgeonSubVo ssv, String thirdPrint, String compId,
            String provinceCode, String year, String compCode, String speType, Integer serialNum) {
        SturgeonSignboardDomestic entity = new SturgeonSignboardDomestic();
        entity.preInsert();
        entity.setCompId(compId);
        entity.setSturgeonSubId(ssv.getId());
        entity.setCaseNum(ssv.getCaseNum());
        entity.setPrintSum(0);
        entity.setThirdPrint(thirdPrint);
        entity.setLabel(ssv.getLabel());
        entity.setLabelPrintStatus(BoolUtils.N);
        entity.setSerial(serialNum);
        String signboard = new StringBuilder("0000").append(provinceCode).
                append(year).append(compCode).append(speType).append(String.format("%08d", serialNum)).toString();
        if (oldSignboards.contains(signboard)) {
            throw new SofnException("标识编码（" + signboard + ")已存在，请检查");
        }
        entity.setSignboard(signboard);
        return entity;
    }

    @Override
    public PageUtils<SturgeonSignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        this.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<SturgeonSignboardDomestic> sturgeonSignboards = ssdMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(sturgeonSignboards)) {
            PageInfo<SturgeonSignboardDomestic> sturgeonSignboardPageInfo = new PageInfo<>(sturgeonSignboards);
            List<SturgeonSignboardVo> sturgeonSignboardVos = Lists.newArrayListWithCapacity(sturgeonSignboards.size());
            for (SturgeonSignboardDomestic sturgeonSignboard : sturgeonSignboards) {
                SturgeonSignboardVo vo = SturgeonSignboardVo.entity2Vo(sturgeonSignboard);
                sturgeonSignboardVos.add(vo);
            }
            PageInfo<SturgeonSignboardVo> monitorVoPageInfo = new PageInfo<>(sturgeonSignboardVos);
            monitorVoPageInfo.setPageSize(pageSize);
            monitorVoPageInfo.setTotal(sturgeonSignboardPageInfo.getTotal());
            monitorVoPageInfo.setPageNum(sturgeonSignboardPageInfo.getPageNum());
            return PageUtils.getPageUtils(monitorVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(sturgeonSignboards));
    }

    public static void perfectParams(Map<String, Object> params) {
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        String organizationLevel = orgInfo.getOrganizationLevel();
        if (CollectionUtils.isEmpty(roles)) {
            throw new SofnException("未获取到该用户角色");
        }
        if (Objects.isNull(params.get("applyType"))) {
            params.put("applyType", "1");
        }
        if (roles.contains(Constants.PRINT_USER_ROLE_CODE)) {
            params.put("isPrint", BoolUtils.Y);
        } else if (roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        } else if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
            params.put("isMinistry" + params.get("applyType"), BoolUtils.Y);
        } else {
            params.put("direclyId", UserUtil.getLoginUserOrganizationId());
        }

    }

    @Override
    public List<SturgeonSignboardVo> list(Map<String, Object> params) {
        this.perfectParams(params);
        List<SturgeonSignboardDomestic> sturgeonSignboards = ssdMapper.listByParams(params);
        List<SturgeonSignboardVo> sturgeonSignboardVos = Lists.newArrayListWithCapacity(sturgeonSignboards.size());
        for (SturgeonSignboardDomestic sturgeonSignboard : sturgeonSignboards) {
            SturgeonSignboardVo vo = SturgeonSignboardVo.entity2Vo(sturgeonSignboard);
            sturgeonSignboardVos.add(vo);
        }
        return sturgeonSignboardVos;
    }

    @Override
    public Integer getPrintNum(String signboardId) {
        return ssdMapper.getPrintNum(signboardId);
    }

    @Override
    public void updatePrintStatusBySturgeonSubId(String sturgeonSubId) {
        ssdMapper.updatePrintStatusBySturgeonSubIdAB(sturgeonSubId);
        ssdMapper.updatePrintStatusBySturgeonSubIdS(sturgeonSubId);
    }

    @Override
    public List<SturgeonSignboardDomestic> listBySturgeonSubIds(List<String> sturgeonSubIds, String printStatus) {
        QueryWrapper<SturgeonSignboardDomestic> queryWrapper = new QueryWrapper<>();
        String finalPrintStatus = StringUtils.hasText(printStatus) ? printStatus : "N";
        queryWrapper.eq("del_flag", BoolUtils.N).in("sturgeon_sub_id", sturgeonSubIds).
                and(wrapper -> wrapper.eq("sticker_print_status", finalPrintStatus)
                        .or().eq("label_print_status", finalPrintStatus));
        return ssdMapper.selectList(queryWrapper);
    }

    @Override
    public void print(String label, List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            //验证是否已打印
            QueryWrapper<SturgeonSignboardDomestic> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("LABEL", label).in("ID", ids);
            if ("A".equals(label) || "B".equals(label)) {
                queryWrapper.eq("LABEL_PRINT_STATUS", BoolUtils.Y);
            } else if ("S".equals(label)) {
                queryWrapper.eq("STICKER_PRINT_STATUS", BoolUtils.Y);
            }
            List<SturgeonSignboardDomestic> sturgeonSignboardDomestics = ssdMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(sturgeonSignboardDomestics)) {
                sturgeonSignboardDomestics.forEach(sturgeonSignboard -> {
                    throw new SofnException("标识号码(" + sturgeonSignboard.getSignboard() + ")已打印,请重新选择！");
                });
            }
            //打印
            Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
            params.put("label", label);
            params.put("ids", ids);
            ssdMapper.print(params);
            //打印完成后，改变国内鱼子酱流程状态
            sturgeonService.updateApplyStatus();
        }
    }

    @Override
    public List<SturgeonSignboardDomestic> listRepring(String compId, List<String> signboardIds, String delFlag) {
        QueryWrapper<SturgeonSignboardDomestic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", delFlag).
                eq("comp_id", StringUtils.hasText(compId) ? compId : UserUtil.getLoginUserOrganizationId()).
                and(wrapper -> wrapper.eq("sticker_print_status", BoolUtils.Y).
                        or().eq("label_print_status", BoolUtils.Y)).
                or().in(!CollectionUtils.isEmpty(signboardIds), "id", signboardIds);
        queryWrapper.select("id", "signboard");
        return ssdMapper.selectList(queryWrapper);
    }

    @Override
    public void updatePirngStatusBySturgeonIds(List<String> signboardIds, String thirdPrint, String printStatus) {
        for (String signboardId : signboardIds) {
            UpdateWrapper<SturgeonSignboardDomestic> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.ne("label", "S").eq("id", signboardId);
            updateWrapper1.set("label_print_status", printStatus).
                    set(StringUtils.hasText(thirdPrint), "third_Print", thirdPrint);
            ssdMapper.update(null, updateWrapper1);

            UpdateWrapper<SturgeonSignboardDomestic> updateWrapper2 = new UpdateWrapper<>();
            updateWrapper2.eq("label", "S").eq("id", signboardId);
            updateWrapper2.set("sticker_print_status", printStatus).
                    set(StringUtils.hasText(thirdPrint), "third_Print", thirdPrint);
            ssdMapper.update(null, updateWrapper2);
        }
    }

    @Override
    public List<SelectVo> getCaseNum(Map<String, Object> params) {
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(roles) && roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        }
        List<String> nums = ssdMapper.getCaseNum(params);
        if (!CollectionUtils.isEmpty(nums)) {
            List<SelectVo> res = Lists.newArrayListWithCapacity(nums.size());
            for (String str : nums) {
                SelectVo vo = new SelectVo();
                vo.setKey(str);
                vo.setVal(str);
                res.add(vo);
            }
            return res;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<SturgeonSignboardDomestic> listSignboardBySignboardIds(List<String> signboardIds) {
        QueryWrapper<SturgeonSignboardDomestic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).in("id", signboardIds);
        queryWrapper.select("signboard", "label");
        return ssdMapper.selectList(queryWrapper);
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<SturgeonSignboardDomestic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return ssdMapper.delete(queryWrapper);
    }

}
