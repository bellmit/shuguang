package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.ThresholdEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.ThresholdValueManagerMapper;
import com.sofn.ducss.mapper.ThresholdYearManagerMapper;
import com.sofn.ducss.model.ThresholdValueManager;
import com.sofn.ducss.model.ThresholdYearManager;
import com.sofn.ducss.service.ThresholdValueManagerService;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.vo.ThresholdValueManagerVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ThresholdValueManagerServiceImpl extends ServiceImpl<ThresholdValueManagerMapper, ThresholdValueManager> implements ThresholdValueManagerService {

    @Autowired
    private ThresholdValueManagerMapper thresholdValueManagerMapper;

    @Autowired
    private ThresholdYearManagerMapper thresholdYearManagerMapper;

    /**
     * 获取信息
     *
     * @param year      年份
     * @param tableName 表格名称
     * @param tableNo   枚举编号
     * @return Map<String, String>
     */
    private Map<String, String> getInfo(String year, String tableName, String tableNo) {
        Map<String, String> map = Maps.newHashMap();
        map.put("year", year);
        map.put("tableName", tableName);
        map.put("tableType", tableNo);
        return map;
    }

    @Override
    public List<Map<String, String>> getYearTableList(String year) {
        List<Map<String, String>> infos = Lists.newArrayList();
        Map<String, String> info1 = getInfo(year, ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_SJSH.getDescription(), ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_SJSH.getCode());
        Map<String, String> info2 = getInfo(year, ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_CSQKHZ.getDescription(), ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_CSQKHZ.getCode());
        Map<String, String> info3 = getInfo(year, ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_LYQKHZ.getDescription(), ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_LYQKHZ.getCode());
        Map<String, String> info4 = getInfo(year, ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_HTLTQK.getDescription(), ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_HTLTQK.getCode());
        infos.add(info1);
        infos.add(info2);
        infos.add(info3);
        infos.add(info4);
        return infos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editValue(List<ThresholdValueManagerVo> thresholdValueManagerVos) {
        if (CollectionUtils.isEmpty(thresholdValueManagerVos)) {
            throw new SofnException("阈值信息不能为空");
        }
        // ID不做任何关联，所以可以直接删除，  查询的时候时候年度和表类型做外键关联
        // 1. 查询当前表类型的信息
        String year = thresholdValueManagerVos.get(0).getYear();
        String tableType = thresholdValueManagerVos.get(0).getTableType();


        // 2. 校验值是否符合规定
        // 比较值大小
        for (ThresholdValueManagerVo thresholdValueManagerVo : thresholdValueManagerVos) {
            String value1 = thresholdValueManagerVo.getValue1();
            String unit = "万吨";
            String valueManagerTargetType = ThresholdEnum.getEnumDes("VALUE_MANAGER_TARGET_TYPE", thresholdValueManagerVo.getTargetType());
            // 提取()中的值
            if(valueManagerTargetType.contains("（") && valueManagerTargetType.contains("）")){
                try{
                    unit = valueManagerTargetType.split("（")[1].split("）")[0];
                }catch (Exception e){
                    e.printStackTrace();
                    unit = "万吨";
                }
            }

            BigDecimal maxValue = new BigDecimal(9999999.99);
            if (!StringUtils.isBlank(value1)) {
                BigDecimal bigDecimal = new BigDecimal(value1);
                if (maxValue.compareTo(bigDecimal) < 0) {
                    throw new SofnException("阈值只能小于" + maxValue.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + unit);
                }

            }

            String value2 = thresholdValueManagerVo.getValue2();
            if (!StringUtils.isBlank(value2)) {
                BigDecimal bigDecimal = new BigDecimal(value2);
                if (maxValue.compareTo(bigDecimal) < 0) {
                    throw new SofnException("阈值只能小于" + maxValue.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + unit);
                }

            }
        }


        String valueManagerTableType = ThresholdEnum.getEnumDes("VALUE_MANAGER_TABLE_TYPE", tableType);
        if (StringUtils.isBlank(valueManagerTableType)) {
            throw new SofnException("表类型异常");
        }

        for (ThresholdValueManagerVo thresholdValueManagerVo : thresholdValueManagerVos) {
            if (thresholdValueManagerVo == null) {
                throw new SofnException("传入信息有误");
            }

            String targetType = thresholdValueManagerVo.getTargetType();
            String valueManagerTargetType = ThresholdEnum.getEnumDes("VALUE_MANAGER_TARGET_TYPE", targetType);
            if (StringUtils.isBlank(valueManagerTargetType)) {
                throw new SofnException("指标类型：【" + targetType + "】无效, 指标类型格式为x-x。 出现这个错误为前端传值问题，请检查！");
            }
            // 如果有值就必填
            String value1 = thresholdValueManagerVo.getValue1();
            String value2 = thresholdValueManagerVo.getValue2();
            boolean flag = false;
            try {
                if (StringUtils.isNotBlank(value1)) {
                    flag = true;
                }

                if (StringUtils.isNotBlank(value2)) {
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SofnException("阈值只能是整数或者小数");
            }
            if (flag) {
                String valueManagerComputerType = ThresholdEnum.getEnumDes("VALUE_MANAGER_COMPUTER_TYPE", thresholdValueManagerVo.getComputerType());
                if (StringUtils.isBlank(valueManagerComputerType)) {
                    throw new SofnException("指标计算类型值：【" + thresholdValueManagerVo.getComputerType() + "】无效， 1.数值 2.比例");
                }
            }
        }

        Set<String> tableTypes = thresholdValueManagerVos.stream().map(ThresholdValueManagerVo::getTableType).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(tableTypes) || tableTypes.size() > 1) {
            throw new SofnException("传入值异常，同一批次只能添加一个表格类型");
        }

        // 3. 如果有信息直接删除
        List<ThresholdValueManagerVo> infos = getInfo(year, tableType);
        if (!CollectionUtils.isEmpty(infos)) {
            // 直接删除年份和表，类型对应的
            thresholdValueManagerMapper.delete(new UpdateWrapper<ThresholdValueManager>()
                    .eq("year", year)
                    .eq("table_type", tableType)
            );

        }
        // 新增
        List<ThresholdValueManager> collect = Lists.newArrayList();
        thresholdValueManagerVos.forEach(item -> {
            if (StringUtils.isNotBlank(item.getComputerType())) {
                ThresholdValueManager thresholdValueManager = ThresholdValueManagerVo.getThresholdValueManager(item);
                thresholdValueManager.setId(IdUtil.getUUId());
                collect.add(thresholdValueManager);
            }
        });
//        saveBatch(collect);
        thresholdValueManagerMapper.batchSaveThresholdValue(collect);
        LogUtil.addLog(LogEnum.LOG_TYPE_THRESHOLD_EDIT.getCode(), "编辑-" + year + "年阈值");
    }

    @Override
    public List<ThresholdValueManagerVo> getInfo(String year, String tableType) {
        if (StringUtils.isBlank(year)) {
            throw new SofnException("年份必填");
        }

        if (StringUtils.isBlank(tableType)) {
            throw new SofnException("表类型必填");
        }

        List<ThresholdYearManager> year1 = thresholdYearManagerMapper.selectList(new QueryWrapper<ThresholdYearManager>().eq("year", year));
        if (CollectionUtils.isEmpty(year1)) {
            return null;
        }
        List<ThresholdValueManager> thresholdValueManagers = thresholdValueManagerMapper.selectList(
                new QueryWrapper<ThresholdValueManager>()
                        .eq("year", year)
                        .eq("table_type", tableType)
        );
        // 先构造表格
        Map<String, String> codeList = ThresholdEnum.getCodeList("VALUE_MANAGER_TARGET_TYPE_" + tableType);

        Map<String, ThresholdValueManager> keyValue = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(thresholdValueManagers)) {
            thresholdValueManagers.forEach(item -> keyValue.put(item.getTargetType(), item));
        }
        List<ThresholdValueManagerVo> thresholdValueManagerVos = Lists.newArrayList();
        for (String key : codeList.keySet()) {
            ThresholdValueManagerVo thresholdValueManagerVo = new ThresholdValueManagerVo();
            thresholdValueManagerVo.setYear(year);
            thresholdValueManagerVo.setTableType(tableType);
            thresholdValueManagerVo.setTargetType(key);
            thresholdValueManagerVo.setTargetTypeName(codeList.get(key));
            ThresholdValueManager thresholdValueManager = keyValue.get(key);
            if (thresholdValueManager != null) {
                thresholdValueManagerVo.setComputerType(thresholdValueManager.getComputerType() == null ? "" : thresholdValueManager.getComputerType());
                thresholdValueManagerVo.setOperate1(thresholdValueManager.getOperate1() == null ? "" : thresholdValueManager.getOperate1());
                thresholdValueManagerVo.setOperate2(thresholdValueManager.getOperate2() == null ? "" : thresholdValueManager.getOperate2());
                thresholdValueManagerVo.setValue1(thresholdValueManager.getValue1() == null ? "" : thresholdValueManager.getValue1().setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                thresholdValueManagerVo.setValue2(thresholdValueManager.getValue2() == null ? "" : thresholdValueManager.getValue2().setScale(2,BigDecimal.ROUND_HALF_UP).toString());
            }
            thresholdValueManagerVos.add(thresholdValueManagerVo);
        }
        return thresholdValueManagerVos;
    }

    @Override
    public List<ThresholdValueManager> getInfo(String year) {
        return thresholdValueManagerMapper.selectList(
                new QueryWrapper<ThresholdValueManager>()
                        .eq("year", year)
        );
    }

    @Override
    public void deleteByYear(String year) {
        if (StringUtils.isBlank(year)) {
            throw new SofnException("year必填");
        }
        thresholdValueManagerMapper.delete(new UpdateWrapper<ThresholdValueManager>().eq("year", year));

    }

    @Override
    public void batchSave(List<ThresholdValueManager> thresholdValueManagerVos) {
        if (!CollectionUtils.isEmpty(thresholdValueManagerVos)) {
            saveBatch(thresholdValueManagerVos);
        }
    }


}
