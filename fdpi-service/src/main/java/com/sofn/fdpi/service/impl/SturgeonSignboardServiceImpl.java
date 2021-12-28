package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.mapper.SturgeonSignboardMapper;
import com.sofn.fdpi.model.Sturgeon;
import com.sofn.fdpi.model.SturgeonSignboard;
import com.sofn.fdpi.service.SturgeonSignboardService;
import com.sofn.fdpi.service.SturgeonSubService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SturgeonSignboardVo;
import com.sofn.fdpi.vo.SturgeonSubVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service(value = "sturgeonSignboardService")
public class SturgeonSignboardServiceImpl implements SturgeonSignboardService {

    @Resource
    private SturgeonSignboardMapper sturgeonSignboardMapper;
    @Resource
    private SturgeonSubService sturgeonSubService;
    @Resource
    private SysRegionApi sysRegionApi;


    @Override
    public void savaBatch(Sturgeon sturgeon, List<SturgeonSubVo> sturgeonSubVos, String type) {
        if (!CollectionUtils.isEmpty(sturgeonSubVos)) {
            Date now = new Date();
            for (SturgeonSubVo sturgeonSubVo : sturgeonSubVos) {
                String startNumStr = sturgeonSubVo.getStartNum();
                Integer startNum = Integer.parseInt(startNumStr);
                Integer endNum = Integer.parseInt(sturgeonSubVo.getEndNum());
                //验证重复
                if ("1".equals(type)) {
                    this.validRrepeat(sturgeon, sturgeonSubVo, startNumStr, "", startNum, endNum);
                } else if ("2".equals(type)) {
                    this.validRrepeat(sturgeon, sturgeonSubVo, startNumStr, sturgeonSubVo.getEndNum(), null, null);
                }
                if ("2".equals(type)) {
                    SturgeonSignboard entity = this.generateSturgeonSignboard(sturgeon, sturgeonSubVo, type, now);
                    entity.setId(IdUtil.getUUId());
                    entity.setSignboard(this.generateSignboard(sturgeon, startNum, endNum,
                            null, sturgeonSubVo.getVariety(), type, startNumStr.length()));
                    sturgeonSignboardMapper.insert(entity);
                } else if ("1".equals(type)) {
                    Integer applyNum = endNum - startNum + 1;
                    Integer splitNum = 100;
                    if (applyNum > 500) {
                        splitNum = 500;
                    }
                    //最后一次提交数量
                    Integer theLastNum = applyNum % splitNum;
                    Boolean flag = theLastNum != 0;
                    Integer totalCommit = applyNum / splitNum;
                    if (flag) {
                        totalCommit += 1;
                    }
                    for (int i = 0; i < totalCommit; i++) {
                        List<SturgeonSignboard> list = null;
                        if (flag && i == totalCommit - 1) {
                            list = Lists.newArrayListWithCapacity(theLastNum);
                            for (int j = 0; j < theLastNum; j++) {
                                SturgeonSignboard entity =
                                        this.generateSturgeonSignboard(sturgeon, sturgeonSubVo, type, now);
                                entity.setId(IdUtil.getUUId());
                                entity.setSerial(startNum);
                                entity.setSignboard(this.generateSignboard(sturgeon, startNum, endNum, startNum++,
                                        sturgeonSubVo.getVariety(), type, startNumStr.length()));
                                list.add(entity);
                            }
                        } else {
                            list = Lists.newArrayListWithCapacity(splitNum);
                            for (int j = 0; j < splitNum; j++) {
                                SturgeonSignboard entity =
                                        this.generateSturgeonSignboard(sturgeon, sturgeonSubVo, type, now);
                                entity.setId(IdUtil.getUUId());
                                entity.setSerial(startNum);
                                entity.setSignboard(this.generateSignboard(sturgeon, startNum, endNum, startNum++,
                                        sturgeonSubVo.getVariety(), type, startNumStr.length()));
                                list.add(entity);
                            }
                        }
                        sturgeonSignboardMapper.insertSturgeonSignboardBatch(list);
                    }
                    SturgeonSignboard entity = this.generateSturgeonSignboard(sturgeon, sturgeonSubVo, type, now);
                    sturgeonSignboardMapper.updateSturgeonSignboardInfo(entity, entity.getSturgeonSubId());
                }
            }
        }
    }

    private SturgeonSignboard generateSturgeonSignboard(
            Sturgeon sturgeon, SturgeonSubVo sturgeonSubVo, String type, Date generatDate) {
        SturgeonSignboard entity = new SturgeonSignboard();
        entity.setCaseNum(sturgeonSubVo.getCaseNum());
        entity.setCompId(sturgeon.getCompId());
        entity.setCredentials(sturgeon.getCredentials());
        entity.setSturgeonSubId(sturgeonSubVo.getId());
        entity.setPrintSum(0);
        entity.setType(type);
        if ("1".equals(type)) {
            entity.setLabel(sturgeonSubVo.getLabel());
            entity.setLabelPrintStatus(BoolUtils.N);
        } else if ("2".equals(type)) {
            entity.setLabel("S");
            entity.setStickerPrintStatus(BoolUtils.N);
        }
        entity.setCreateTime(generatDate);
        return entity;
    }

    @Override
    public PageUtils<SturgeonSignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(roles) && roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<SturgeonSignboard> sturgeonSignboards = sturgeonSignboardMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(sturgeonSignboards)) {
            PageInfo<SturgeonSignboard> sturgeonSignboardPageInfo = new PageInfo<>(sturgeonSignboards);
            List<SturgeonSignboardVo> sturgeonSignboardVos = Lists.newArrayListWithCapacity(sturgeonSignboards.size());
            for (SturgeonSignboard sturgeonSignboard : sturgeonSignboards) {
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

    @Override
    public List<SturgeonSignboardVo> list(Map<String, Object> params) {
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(roles) && roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        }
        List<SturgeonSignboard> sturgeonSignboards = sturgeonSignboardMapper.listByParams(params);
        List<SturgeonSignboardVo> sturgeonSignboardVos = Lists.newArrayListWithCapacity(sturgeonSignboards.size());
        for (SturgeonSignboard sturgeonSignboard : sturgeonSignboards) {
            SturgeonSignboardVo vo = SturgeonSignboardVo.entity2Vo(sturgeonSignboard);
            sturgeonSignboardVos.add(vo);
        }
        return sturgeonSignboardVos;
    }

    @Override
    @Transactional
    public void print(String label, List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            //验证是否已打印
            QueryWrapper<SturgeonSignboard> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("LABEL", label).in("ID", ids);
            if ("A".equals(label) || "B".equals(label)) {
                queryWrapper.eq("LABEL_PRINT_STATUS", BoolUtils.Y);
            } else if ("S".equals(label)) {
                queryWrapper.eq("STICKER_PRINT_STATUS", BoolUtils.Y);
            }
            List<SturgeonSignboard> sturgeonSignboards = sturgeonSignboardMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(sturgeonSignboards)) {
                sturgeonSignboards.forEach(sturgeonSignboard -> {
                    throw new SofnException("标识号码(" + sturgeonSignboard.getSignboard() + ")已打印,请重新选择！");
                });
            }
            //打印
            Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
            params.put("label", label);
            params.put("ids", ids);
            sturgeonSignboardMapper.print(params);
        }
    }

    @Override
    public List<SelectVo> getCaseNum(Map<String, Object> params) {
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(roles) && roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        }
        List<String> nums = sturgeonSignboardMapper.getCaseNum(params);
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
    public void updatePirngStatusBySturgeonId(String sturgeonId, String printStatus) {
        List<SturgeonSubVo> sturgeonSubVos = sturgeonSubService.listBySturgeonId(sturgeonId);
        if (!CollectionUtils.isEmpty(sturgeonSubVos)) {
            sturgeonSubVos.forEach(sturgeonSubVo -> {
                String id = sturgeonSubVo.getId();
                SturgeonSignboard entity1 = new SturgeonSignboard();
                entity1.setLabelPrintStatus(printStatus);
                UpdateWrapper<SturgeonSignboard> updateWrapper1 = new UpdateWrapper<>();
                updateWrapper1.ne("LABEL", "S").eq("STURGEON_SUB_ID", id);
                sturgeonSignboardMapper.update(entity1, updateWrapper1);
                SturgeonSignboard entity2 = new SturgeonSignboard();
                entity2.setStickerPrintStatus(printStatus);
                UpdateWrapper<SturgeonSignboard> updateWrapper2 = new UpdateWrapper<>();
                updateWrapper2.eq("LABEL", "S").eq("STURGEON_SUB_ID", id);
                sturgeonSignboardMapper.update(entity2, updateWrapper2);
            });
        }
    }

    private String generateSignboard(Sturgeon sturgeon, Integer startNum, Integer endNum, Integer serial,
                                     String variety, String type, Integer numLength) {
        StringBuilder sb = new StringBuilder();
        String credentials = sturgeon.getCredentials();
        sb.append(variety).append("/");
        sb.append(sturgeon.getSource()).append("/");
        sb.append(sturgeon.getOrigin()).append("/");
        sb.append(credentials.substring(0, 4)).append("/");
        sb.append(sturgeon.getHandle()).append("/");
        if ("1".equals(type)) {
            sb.append(String.format("%0" + numLength.toString() + "d", serial)).append("/");
        } else if ("2".equals(type)) {
            sb.append(String.format("%0" + numLength.toString() + "d", startNum)).append("-").
                    append(String.format("%0" + numLength.toString() + "d", endNum)).append("/");
        }
        String str[] = credentials.split("/");
        if (str.length != 3) {
            throw new SofnException("证书编号填写有误,请检查！");
        }
        sb.append(str[1]).append("/");
        sb.append(sturgeon.getIssueAddr());
        return sb.toString();
    }

    /**
     * 验证重复
     */
    @Override
    public void validRrepeat2(SturgeonSubVo sturgeonSubVo, Integer startNum, Integer endNum) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(4);
        params.put("year", DateUtils.format(new Date(), "YYYY"));
        params.put("variety", sturgeonSubVo.getVariety());
        params.put("startNum", startNum);
        params.put("endNum", endNum);
        List<String> signboards = sturgeonSignboardMapper.listSignboardsByParams2(params);
        if (!CollectionUtils.isEmpty(signboards)) {
            signboards.forEach(serial -> {
                Map<String, String> citesMap = sysRegionApi.getDictListByType("fdpi_cites").getData().
                        stream().collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));
                throw new SofnException("品种（" + citesMap.get(sturgeonSubVo.getVariety()) + "）今年已使用号段" +
                        String.format("%05d", Integer.valueOf(serial)) + ",不可再使用");
            });
        }
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<SturgeonSignboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return sturgeonSignboardMapper.delete(queryWrapper);
    }

    /**
     * 验证重复
     */
    private void validRrepeat(Sturgeon sturgeon, SturgeonSubVo sturgeonSubVo,
                              String startStr, String endStr, Integer startNum, Integer endNum) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(8);
        params.put("credentials", sturgeon.getCredentials());
        params.put("variety", sturgeonSubVo.getVariety());
        params.put("source", sturgeon.getSource());
        params.put("origin", sturgeon.getOrigin());
        params.put("handle", sturgeon.getHandle());
        params.put("issueAddr", sturgeon.getIssueAddr());
        params.put("startNum", startNum);
        params.put("endNum", endNum);
        if (StringUtils.hasText(startStr) && StringUtils.hasText(endStr)) {
            params.put("signboard", this.generateSignboard(sturgeon, Integer.parseInt(startStr), Integer.parseInt(endStr),
                    null, sturgeonSubVo.getVariety(), "2", startStr.length()));
        }
        List<String> signboards = sturgeonSignboardMapper.listSignboardsByParams(params);
        if (!CollectionUtils.isEmpty(signboards)) {
            signboards.forEach(signboard -> {
                throw new SofnException("标识号码(" + signboard + ")重复,请修改！");
            });
        }
    }
}
