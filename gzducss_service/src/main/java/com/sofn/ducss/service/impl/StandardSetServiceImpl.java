package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ducss.enums.CropsEnum;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.StandardSetMapper;
import com.sofn.ducss.mapper.StandardValueMapper;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.StandardSet;
import com.sofn.ducss.model.StandardValue;
import com.sofn.ducss.service.StandardSetService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.vo.StandarSetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/10/27 18:01
 * @Version 1.0
 * 部级标准值设定
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StandardSetServiceImpl implements StandardSetService {

    @Autowired
    private StandardSetMapper standardSetMapper;

    @Autowired
    private StandardValueMapper standardValueMapper;

    @Autowired
    private SysApi sysApi;

    /**
     * 新增年度标准
     *
     * @param setVo
     */
    @Override
    public Result<Object> add(StandarSetVo setVo) {


        //查询是否有重复年度数据
        Integer count = selectYear(setVo.getBeYear());
        if (count > 0) {
            throw new SofnException(setVo.getBeYear() + "年度标准已添加,请勿重复添加");
        }

        StandardSet standardSet = new StandardSet();
        standardSet.setYear(setVo.getBeYear());
        standardSet.setOperator(UserUtil.getLoginUser().getUsername());
        standardSet.setOperationTime(new Date());
        standardSetMapper.insert(standardSet);
        //standardSetService.add(standardSet);

        //1.判断是否新增数据
        if ("N".equals(setVo.getIfAdd())) {
            //copy一套往年的标椎和值
            String copyYear = setVo.getCopyYear();
            //首次增加
            if (StringUtils.isEmpty(copyYear)) {
                LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_ADD.getCode(), "新增-" + standardSet.getYear() + "年参数");

                return Result.ok(200, "添加年度标准成功");
            }
            //查出一套往年标准值，替换往年的年份为今年
            copystandardValue(copyYear, setVo.getBeYear());

        } else {

            ArrayList<StandardValue> standardValues = setVo.getStandardValues();
            if (standardValues == null || standardValues.size() == 0) {
                LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_ADD.getCode(), "新增-" + standardSet.getYear() + "年参数");

                return Result.ok(200, "添加年度标准成功");

            }
            for (StandardValue standardValue : standardValues) {
                addSetValue(standardValue);
            }
        }
        LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_ADD.getCode(), "新增-" + standardSet.getYear() + "年参数");
        return Result.ok(200, "添加年度标准成功");


    }

    /**
     * copy一套标准
     *
     * @param copyYear
     * @param beYear
     */
    @Override
    public void copystandardValue(String copyYear, String beYear) {
        //查出一套往年标准值，替换往年的年份为今年(pgsql暂时无法使用uuid)
        //standardValueMapper.copystandardValue(copyYear,beYear);

        //查询往年标准，循环设置
        List<StandardValue> standardValues = standardValueMapper.selectList(new QueryWrapper<StandardValue>().eq("YEAR", copyYear));
        for (StandardValue standardValue : standardValues) {
            standardValue.setId(IdUtil.getUUId());
            standardValue.setYear(beYear);
            standardValueMapper.insert(standardValue);
        }


    }

    /**
     * 新增一套标准值
     *
     * @param standardValue
     */
    @Override
    public void addSetValue(StandardValue standardValue) {
        standardValueMapper.insert(standardValue);
    }

    /**
     * 查询是否有当前年度的数据
     *
     * @param beYear
     * @return
     */
    @Override
    public int selectYear(String beYear) {

        Integer count = standardSetMapper.selectCount(new QueryWrapper<StandardSet>().eq("year", beYear));

        return count;

    }

    /**
     * 分页查询年度标准
     *
     * @param pageNo
     * @param pageSize
     * @param year
     * @return
     */
    @Override
    public Result<PageUtils<StandardSet>> selectPage(Integer pageNo, Integer pageSize, String year) {
        PageHelper.offsetPage(pageNo, pageSize);

        ArrayList<StandardSet> list = standardSetMapper.getStandardSetList(year);
        PageInfo<StandardSet> standardSetPageInfo = new PageInfo<>(list);


        return Result.ok(PageUtils.getPageUtils(standardSetPageInfo));


    }

    /**
     * 根据年度删除所有标准值
     *
     * @param year
     */
    @Override
    public void deleteByYear(String year) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("year", year);
        int i = standardValueMapper.deleteByMap(map);

    }

    /**
     * 根据年度和区域展示标准值详情
     *
     * @param map
     * @return
     */
    @Override
    public List<StandardValue> showStandardValues(HashMap<String, Object> map) {
        List<StandardValue> standardValues = standardValueMapper.selectByMap(map);

        return standardValues;
    }

    /**
     * 根据年份查询省份Ids
     *
     * @param year
     * @return
     */
    @Override
    public List<String> selectAreaIdsByYear(String year) {
        ArrayList<String> ids = standardValueMapper.selectAreaIdsByYear(year);

        return ids;
    }

    /**
     * 删除原来标准值新增数据
     *
     * @param standardValues
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStandSetValue(List<StandardValue> standardValues) {
        StandardValue standardValue1 = standardValues.get(1);
        String area_id = standardValue1.getAreaId();
        String year = standardValue1.getYear();
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("area_id", area_id);
        prams.put("year", year);
        standardValueMapper.deleteByMap(prams);

        for (StandardValue detail : standardValues) {
            detail.setId(IdUtil.getUUId());
            if (detail.getGrassValley() == null) {
                detail.setGrassValley(new BigDecimal(0));
            }
            if (detail.getCollectCoefficient() == null) {
                detail.setGrassValley(new BigDecimal(0));
            }

            if (detail.getCollectCoefficient().compareTo(new BigDecimal(0)) == -1
                    || detail.getCollectCoefficient().compareTo(new BigDecimal(1)) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的收集系数超过范围[0~1]！");
            }
            //逐个判断各个农作物种类的草谷比输入是否符合要求
            //先判断最大范围的，减少判断次数
            if ((CropsEnum.OTHER.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.COTTON.getName().equalsIgnoreCase(detail.getStrawType())
            ) && detail.getGrassValley().compareTo(CropsEnum.OTHER.getGrassValleyRatio()) == 1
            ) {
                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过最大范围[0~6]！");
            }
            if ((CropsEnum.CORN.getName().equalsIgnoreCase(detail.getStrawType()) ||              //玉米
                    CropsEnum.RAPE.getName().equalsIgnoreCase(detail.getStrawType()) ||            //油菜
                    CropsEnum.PEANUT.getName().equalsIgnoreCase(detail.getStrawType()) ||        //花生
                    CropsEnum.SOYBEAN.getName().equalsIgnoreCase(detail.getStrawType())) &&
                    detail.getGrassValley().compareTo(CropsEnum.CORN.getGrassValleyRatio()) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过[0~" + CropsEnum.CORN.getGrassValleyRatio() + "]范围");
            }

            if ((CropsEnum.EARLY_RICE.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.MIDDLE_RICE.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.DOUBLE_LATE_RICE.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.WHEAT.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.POTATO.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.SWEET_POTATO.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.CASSAVA.getName().equalsIgnoreCase(detail.getStrawType())) &&
                    detail.getGrassValley().compareTo(CropsEnum.EARLY_RICE.getGrassValleyRatio()) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过[0~" + CropsEnum.EARLY_RICE.getGrassValleyRatio() + "]范围");
            }

            if ((CropsEnum.SUGAR_CANE.getName().equalsIgnoreCase(detail.getStrawType()))         //甘蔗
                    && detail.getGrassValley().compareTo(CropsEnum.SUGAR_CANE.getGrassValleyRatio()) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过[0~" + CropsEnum.SUGAR_CANE.getGrassValleyRatio() + "]范围");
            }

            standardValueMapper.insert(detail);


        }


//		//删除后从新添加
//		for (StandardValue standardValue : standardValues) {
//			standardValue.setId(IdUtil.getUUId());
//			//系数标准判断
//			BigDecimal collectCoefficient = standardValue.getCollectCoefficient();
//			BigDecimal grassValley = standardValue.getGrassValley();
//			//如果为空系数为空，也可以添加成功
//			if (collectCoefficient!=null){
//				if (collectCoefficient.compareTo(new BigDecimal(0))==-1||
//						collectCoefficient.compareTo(new BigDecimal(1))==1){
//					throw new SofnException("[" + standardValue.getStrawName() + "]的可收集系数过范围[0~1]！");
//				}
//				standardValue.setCollectCoefficient(collectCoefficient.setScale(2,BigDecimal.ROUND_HALF_UP));
//			}if (grassValley!=null){
//				if (grassValley.compareTo(new BigDecimal(0))==-1||
//						grassValley.compareTo(new BigDecimal(10))>-1){
//					throw new SofnException("[" + standardValue.getStrawName() + "]的草谷比超过范围[0~10]！");
//				}
//				standardValue.setCollectCoefficient(grassValley.setScale(2,BigDecimal.ROUND_HALF_UP));
//			}
//
//
//			standardValueMapper.insert(standardValue);
//		}

    }

    /**
     * 查询可选复制年份
     *
     * @return
     */
    @Override
    public Result<List<String>> selectCopyYear() {
        List<String> copyYear = standardSetMapper.selectCopyYear();

        return Result.ok(copyYear);
    }

    /**
     * 刪除标准年度
     *
     * @param year
     */
    @Override
    public void deleteStandardByYear(String year) {
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("year", year);
        standardSetMapper.deleteByMap(prams);

    }

    /**
     * 根据年份和省区域id查询系数
     *
     * @param year
     * @param province
     * @return
     */
    @Override
    public List<StandardValue> showStandardValues(String year, String province) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("area_id", province);
        //当前区域值
        List<StandardValue> standardValues = showStandardValues(map);

        //查询全国区域值
        map.put("year", year);
        map.put("area_id", "100000");
        List<StandardValue> standardValuesCountry = showStandardValues(map);
        //如果 标准为空直接返回全国
        if (standardValues == null || standardValues.size() == 0) {
            return standardValuesCountry;
        }
        if (standardValuesCountry != null && standardValuesCountry.size() > 0) {
            for (int i = 0; i < standardValuesCountry.size(); i++) {
                for (int i1 = 0; i1 < standardValues.size(); i1++) {
                    StandardValue standardCountry = standardValuesCountry.get(i);
                    StandardValue standardArea = standardValues.get(i1);
                    if (standardCountry.getStrawType().equals(standardArea.getStrawType())) {
                        if (standardArea.getCollectCoefficient() == null || standardArea.getCollectCoefficient().compareTo(new BigDecimal(0)) == 0) {
                            //用全国标准值
                            standardArea.setCollectCoefficient(standardCountry.getCollectCoefficient());
                        }
                        if (standardArea.getGrassValley() == null || standardArea.getGrassValley().compareTo(new BigDecimal(0)) == 0) {
                            //用全国标准值
                            standardArea.setGrassValley(standardCountry.getGrassValley());
                        }
                        break;
                    }
                }
            }
            return standardValues;
        } else {
            return standardValues;
        }


    }

    @Override
    public Result<List<StandardValue>> getGcByProvince(String year, String areaId, Boolean ifBack) {
        //查询全国区域值直接返回
        if (areaId.equals("100000")) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("year", year);
            map.put("area_id", areaId);
            //当前区域值
            List<StandardValue> standardValues = showStandardValues(map);
            return Result.ok(standardValues);
        } else {
            return getStandardValues(year, areaId, ifBack);
        }
    }

    @Override
    public Result<List<StandardValue>> getGcByAreaId(String year, String areaId) {
        //根据县级id查询省id
        List<SysRegionTreeVo> data = sysApi.getParentNodeByRegionCode(areaId);
        if (data == null || data.size() == 0) {
            throw new SofnException("区域id无法查询到草谷比和可收集系数比标准值");
        }
        SysRegionTreeVo sysRegionTreeVo = data.get(0);
        String provinceId = sysRegionTreeVo.getRegionCode();
        if (StringUtils.isEmpty(provinceId)) {
            throw new SofnException("区域id无法查询到草谷比和可收集系数比标准值");

        }
        return getStandardValues(year, provinceId, true);

    }


    /**
     * 查询当前区划标准值，为0为空则用全国标准值
     *
     * @param year
     * @param areaId
     * @return
     */
    private Result<List<StandardValue>> getStandardValues(String year, String areaId, boolean ifBack) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("area_id", areaId);
        //当前区域值
        List<StandardValue> standardValues = showStandardValues(map);
        if (!ifBack) {
            return Result.ok(standardValues);
        }

        //查询全国区域值
        map.put("year", year);
        map.put("area_id", "100000");
        List<StandardValue> standardValuesCountry = showStandardValues(map);
        //如果 标准为空直接返回全国
        if (standardValues == null || standardValues.size() == 0) {
            return Result.ok(standardValuesCountry);
        }
        if (standardValuesCountry != null && standardValuesCountry.size() > 0) {
            for (int i = 0; i < standardValuesCountry.size(); i++) {
                for (int i1 = 0; i1 < standardValues.size(); i1++) {
                    StandardValue standardCountry = standardValuesCountry.get(i);
                    StandardValue standardArea = standardValues.get(i1);
                    if (standardCountry.getStrawType().equals(standardArea.getStrawType())) {
                        if (standardArea.getCollectCoefficient() == null || standardArea.getCollectCoefficient().compareTo(new BigDecimal(0)) == 0) {
                            //用全国标准值
                            standardArea.setCollectCoefficient(standardCountry.getCollectCoefficient());
                        }
                        if (standardArea.getGrassValley() == null || standardArea.getGrassValley().compareTo(new BigDecimal(0)) == 0) {
                            //用全国标准值
                            standardArea.setGrassValley(standardCountry.getGrassValley());
                        }
                        break;
                    }

                }
            }
            return Result.ok(standardValues);
        } else {
            return Result.ok(standardValues);
        }


    }


}
