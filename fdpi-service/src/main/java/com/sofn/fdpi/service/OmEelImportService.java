package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.OmEelImportFrom;
import com.sofn.fdpi.vo.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 鳗鱼进口管理业务层接口
 * @Author wg
 * @Date 2021/5/12 11:39
 **/
public interface OmEelImportService extends IService<OmEelImportFrom> {
    /**
     * @param omEelImportVo
     * @return boolean
     * @author wg
     * @description 鳗鱼进口新增
     * @date 2021/5/12 14:14
     */
    boolean add(OmEelImportVo omEelImportVo);

    /**
     * @param id
     * @return com.sofn.fdpi.model.OmEelImportFrom
     * @author wg
     * @description 根据主键查询进口欧鳗信息
     * @date 2021/5/12 14:18
     */
    OmImportVo searchByid(String id);

    /**
     * @param
     * @return
     * @author wg
     * @description 查询进口鳗鱼信息列表
     * @date 2021/5/12 16:01
     */
    PageUtils<OmImportFromVo> getListImport(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * @param id
     * @return java.lang.Integer
     * @author wg
     * @description 删除进口欧鳗
     * @date 2021/5/12 19:00
     */
    Integer delEelImport(String id);

    /**
     * @param
     * @return void
     * @author wg
     * @description 更新欧鳗进口数据
     * @date 2021/5/13 9:41
     */
    void updateImport(OmEelImportVo omEelImportFrom);

    /**
     * @param map
     * @param response
     * @return void
     * @author wg
     * @description 进口比例折算导出
     * @date 2021/5/14 14:14
     */
    void export(Map<String, Object> map, HttpServletResponse response);

    /**
     * @param compName
     * @param cred
     * @return com.sofn.fdpi.model.OmEelImportFrom
     * @author wg
     * @description 根据进口企业的名字和《允许进出口证明书》号拿到详情数据
     * @date 2021/5/17 14:12
     */
    OmImportInfo getInfoByNameAndCred(String compName, String cred);

    /**
     * @param compName 企业名称
     * @param cred     允许进出口证明书》号
     * @param num      扣减多少
     * @return void
     * @author wg
     * @description 进口企业扣减库存
     * @date 2021/5/17 14:25
     */
    void deductionRepo(String compName, String cred, Double num);

    /**
     * @param importMan  进口企业名称
     * @param credential 允许进出口证明书号
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @return java.util.List<com.sofn.fdpi.vo.OmHistogram>
     * @author wg
     * @description 进口企业图表分析数据列表
     * @date 2021/5/23 14:59
     */
    List<OmHistogram> getOmHistogram(String importMan, String credential, Date startDate, Date endDate);

    /**
     * @param id
     * @return com.sofn.fdpi.vo.ImportTraceToSourceVo
     * @author wg
     * @description 欧鳗进口企业根据主键得到溯源数据
     * @date 2021/5/24 13:49
     */
    ImportTraceToSourceVo getTraceSourceById(String id);

    /**
     * @param
     * @return java.util.List<com.sofn.fdpi.vo.SelectVo>
     * @author wg
     * @description 养殖企业获得有效的加工企业列表
     * @date 2021/6/3 10:19
     */
    List<SelectVo> getImportList();
}
