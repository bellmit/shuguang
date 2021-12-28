package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.TbComp;
import com.sofn.fdpi.vo.CompAndUserVo;
import com.sofn.fdpi.vo.DepartmentLevelVo;
import com.sofn.fdpi.vo.TbCompVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:  企业
 * @Author: wuXY
 * @Date: 2019-12-30 11:32:03
 */
public interface TbCompMapper extends BaseMapper<TbComp> {
    /**
     * @Description: 根据查询条件获取企业列表
     * @Author: wuXY
     * @Date: 2019-12-30 11:31:41
     * @param map 对象
     * @return  List<TbCompVo> 对象
     */
    List<TbCompVo> ListByCondition(Map<String,Object> map);

    /**
     * @Description: 企业查询-列表
     * @Author: wuXY
     * @Date: 2019-12-30 11:31:41
     * @param map 查询参数
     * @return  List<TbCompVo> 对象
     */
    List<TbCompVo> listForCompAndYearInspect(Map<String,Object> map);

    /**
     * 根据获取企业信息
     * wuXY
     * 2019-12-30 11:31:41
     * @param id 企业id编号
     * @return TbComp对象
     */
    TbCompVo getCombById(@Param("id") String id);

    /**
     * 根据企业id编号修改企业信息
     * wuXY
     * 2019-12-30 11:31:41
     * @param comp 企业对象
     * @return 操作成功
     */
    int updateComById(TbComp comp);

    /**
     * 根据企业id修改企业状态
     * @param map 参数对象
     * @return int
     */
    int updateStatusById(Map<String,Object> map);

    /**
     * 获取当前企业的直属企业信息（id和level）
     * wuXY
     * 2020-1-9 17:23:24
     * @param compId 企业id
     * @return DepartmentLevelVo 直属企业信息
     */
    DepartmentLevelVo getDeptLevel(@Param("compId") String compId);

    /**
     * 直属机构修改企业的行政区划
     * wuXY
     * 2020-1-15 15:33:57
     * @param tbComp 修改的对象
     * @return 1：成功 or 其他失败
     */
    int updateCompRegionById(TbComp tbComp);

    /**
     * 获取所有企业名称和账号；企业名称和账号是1:1，可以写成一个，后期是1：n，则需要分开
     * wXY
     * 2020-7-30 09:38:55
     * @return List<CompAndUserVo>
     */
    List<CompAndUserVo> listForAllCompAndUser();

    int getCompCount(String status);

    String getTodayMaxApplyNum(String todayStr);

    String getMaxCompCode();
}
